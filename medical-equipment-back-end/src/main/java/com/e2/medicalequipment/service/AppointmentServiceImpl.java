package com.e2.medicalequipment.service;

import com.e2.medicalequipment.dto.CreateAppointmentDTO;
import com.e2.medicalequipment.dto.UpdateItemDTO;
import com.e2.medicalequipment.model.*;
import com.e2.medicalequipment.repository.AppointmentRepository;
import com.e2.medicalequipment.repository.CompanyAdministratorRepository;
import com.e2.medicalequipment.repository.EquipmentRepository;
import com.e2.medicalequipment.repository.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PessimisticLockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd yyyy HH:mm:ss 'GMT'Z (zzzz)", Locale.ENGLISH);

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private CompanyAdministratorRepository companyAdministratorRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ItemService itemService;
    @Autowired
    private QRCodeService qrCodeService;
    @Autowired
    private UserService userService;
    @Autowired
    private EquipmentRepository equipmentRepository;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Appointment Create(CreateAppointmentDTO createAppointmentDto){
        try {
            LocalDateTime startTime = LocalDateTime.parse(createAppointmentDto.startTime, formatter);
            LocalDateTime endTime = LocalDateTime.parse(createAppointmentDto.endTime, formatter);

            Appointment appointment = new Appointment();
            appointment.setStartTime(startTime);
            appointment.setEndTime(endTime);
            appointment.setIsPredefined(createAppointmentDto.isPredefined);
            CompanyAdministrator ca = companyAdministratorRepository.findOneById(createAppointmentDto.companyAdministrator.id);
            appointment.setCompanyAdministrator(ca);

            if (appointment.getId() != null) {
                throw new IllegalArgumentException("ID must be null for a new entity.");
            }
            Appointment savedAppointment = appointmentRepository.save(appointment);

            return savedAppointment;
        } catch (PessimisticLockingFailureException e) {
            throw e;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional(readOnly = false)
    public CompanyAdministrator findOneById(Long id){
        try{
            CompanyAdministrator a = companyAdministratorRepository.findOneById(id);
            return a;
        }catch (PessimisticLockingFailureException e) {
            throw e;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean Delete(Long id) throws Exception {
        try{
            appointmentRepository.deleteById(id);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Appointment CreateIrregular(CreateAppointmentDTO createAppointmentDto, String customerId) {
        try{
            Appointment appointment = new Appointment();
            LocalDateTime startTime = LocalDateTime.parse(createAppointmentDto.startTime, formatter);
            appointment.setStartTime(startTime);
            LocalDateTime endTime = LocalDateTime.parse(createAppointmentDto.endTime, formatter);
            appointment.setEndTime(endTime);

            if (appointment.getId() != null) {
                throw new Exception("ID must be null for a new entity.");
            }
            Appointment savedAppointment = appointmentRepository.save(appointment);

            List<Item> updatedItems = UpdateItems(savedAppointment, customerId);
            for (Item i : updatedItems){
                System.out.println(i.getId());
                System.out.println(i.getAppointment());
                System.out.println(i.getCustomer());
            }

            return savedAppointment;
        }catch (PessimisticLockingFailureException e){
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private List<Item> UpdateItems(Appointment savedAppointment, String customerId){
        List<Item> customerItems = itemRepository.findAllByCustomerId(customerId);
        List<Item> currentItems = new ArrayList<>();
        for(Item i : customerItems){
            if(i.getAppointment() == null){
                i.setAppointment(savedAppointment);
                currentItems.add(i);
            }
        }

        int price = 0;
        String message = "Broj rezervacije : " + savedAppointment.getId() + "\n\nRezervisana oprema :\n";

        for(Item item : currentItems){
            price += equipmentRepository.getById(item.getEquipment().getId()).getPrice() * item.getCount();
            message += equipmentRepository.getById(item.getEquipment().getId()).getName()+ " - " + item.getCount() + " kom\n";

            UpdateItemDTO updatedItem = new UpdateItemDTO();
            updatedItem.CompanyId = item.getCompany().getId();
            updatedItem.EquipmentId = item.getEquipment().getId();
            updatedItem.Count = item.getCount();
            updatedItem.CustomerId = item.getCustomer().getId();
            updatedItem.AppointmentId = item.getAppointment().getId();
            updatedItem.Id = item.getId();
            updatedItem.PickedUp = item.isPickedUp();

            itemService.UpdateIrregular(updatedItem);
        }

        qrCodeService.sendQRCode("Your cart", userService.getUserById(Long.parseLong(customerId)).getUsername(), message, savedAppointment.getId());
        return currentItems;
    }

    @Override
    public List<Appointment> GetAll() throws Exception {
        List<Appointment> allAppointments = new ArrayList<>();
        for (Appointment a : appointmentRepository.findAll()) {
            allAppointments.add(a);
        }
        return allAppointments;
    }

    @Override
    public List<Appointment> GetFreeByCompanyId(Long companyId) throws Exception {
        List<Appointment> freeAppointments = new ArrayList<>();
        for (Appointment a : appointmentRepository.findAllByCompanyId(companyId.toString())) {
            if (itemRepository.findAllByAppointmentId(a.getId().toString()).isEmpty()) {
                freeAppointments.add(a);
            }
        }
        return freeAppointments;
    }

    @Override
    public List<Appointment> GetScheduledByCompanyId(Long companyId) throws Exception {
        List<Appointment> scheduledAppointments = new ArrayList<>();
        for (Appointment a : appointmentRepository.findAllByCompanyId(companyId.toString())) {
            List<Item> items = itemRepository.findAllByAppointmentId(a.getId().toString());
            if (!items.isEmpty()) {
                boolean allItemsNotPickedUp = items.stream().allMatch(item -> !item.isPickedUp());
                if (allItemsNotPickedUp) {
                    scheduledAppointments.add(a);
                }
            }
        }
        return scheduledAppointments;
    }

    @Override
    public List<Appointment> GetScheduledByCustomerId(Long customerId) throws Exception{
        List<Appointment> scheduledAppointments = new ArrayList<>();
        for (Item item : itemRepository.findAllByCustomerId(String.valueOf(customerId))){
            Appointment a = item.getAppointment();
            if(!scheduledAppointments.contains(a)) {
                scheduledAppointments.add(a);
            }
        }
        return scheduledAppointments;
    }

    @Override
    public List<Appointment> GetPickedUpByCustomerId(Long customerId) throws Exception{
        List<Appointment> finishedAppointments = new ArrayList<>();
        for (Item item : itemRepository.findAllByCustomerId(String.valueOf(customerId))){
            Appointment a = item.getAppointment();
            if(!finishedAppointments.contains(a) && item.isPickedUp()) {
                finishedAppointments.add(a);
            }
        }
        return finishedAppointments;
    }

    @Override
    public Appointment FindById(Long id) throws Exception {
        return appointmentRepository.getAppointmentById(id);
    }


    public Appointment GetById(Long id) {
        return appointmentRepository.findById(id).orElse(null);
    }
    public boolean CheckReservation(Long id) {
        List<Item> items = itemRepository.findAllByAppointmentId(id.toString());
        Boolean isAvailable = false;
        if (!items.isEmpty()) {
           isAvailable = items.stream().allMatch(item -> !item.isPickedUp() && !item.isQrCodeProcessed());
        }
        return isAvailable;
    }



    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public List<CompanyAdministrator> getFreeAdminsForAppointment(String startTime){
        try {
            List<CompanyAdministrator> admins = new ArrayList<>();
            LocalDateTime start = LocalDateTime.parse(startTime, formatter);

            if (appointmentRepository.findAllByStartTime(start) != null) {
                for (Appointment a : appointmentRepository.findAllByStartTime(start)) {
                    admins.add(a.getCompanyAdministrator());
                }
                return admins;
            } else {
                return null;
            }
        }catch (PessimisticLockingFailureException e){
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
