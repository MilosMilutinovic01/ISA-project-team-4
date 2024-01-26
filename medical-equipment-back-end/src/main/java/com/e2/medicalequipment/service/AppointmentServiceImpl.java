package com.e2.medicalequipment.service;

import com.e2.medicalequipment.dto.CreateAppointmentDTO;
import com.e2.medicalequipment.model.*;
import com.e2.medicalequipment.repository.AppointmentRepository;
import com.e2.medicalequipment.repository.CompanyAdministratorRepository;
import com.e2.medicalequipment.repository.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public Appointment Create(CreateAppointmentDTO createAppointmentDto) throws Exception {

        Appointment appointment = new Appointment();
        LocalDateTime startTime = LocalDateTime.parse(createAppointmentDto.startTime, formatter);
        appointment.setStartTime(startTime);
        LocalDateTime endTime = LocalDateTime.parse(createAppointmentDto.endTime, formatter);
        appointment.setEndTime(endTime);
        appointment.setIsPredefined(createAppointmentDto.isPredefined);
        CompanyAdministrator ca = companyAdministratorRepository.findById(createAppointmentDto.companyAdministrator.id).orElseThrow(() -> new EntityNotFoundException("Company admin not found"));
        appointment.setCompanyAdministrator(ca);


        if (appointment.getId() != null) {
            throw new Exception("ID must be null for a new entity.");
        }
        Appointment savedAppointment = appointmentRepository.save(appointment);
        return savedAppointment;
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
    public Appointment CreateIrregular(CreateAppointmentDTO createAppointmentDto) throws Exception {

        Appointment appointment = new Appointment();
        LocalDateTime startTime = LocalDateTime.parse(createAppointmentDto.startTime, formatter);
        appointment.setStartTime(startTime);
        LocalDateTime endTime = LocalDateTime.parse(createAppointmentDto.endTime, formatter);
        appointment.setEndTime(endTime);

        if (appointment.getId() != null) {
            throw new Exception("ID must be null for a new entity.");
        }
        Appointment savedAppointment = appointmentRepository.save(appointment);
        return savedAppointment;
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
        return appointmentRepository.findAppointmentById(id);
    }


    public Appointment GetById(Long id) {
        return appointmentRepository.findById(id).orElse(null);
    }

    public boolean CheckReservation(Long id) {
        List<Item> items = itemRepository.findAllByAppointmentId(id.toString());
        Boolean isAvailable = false;
        if (!items.isEmpty()) {
            isAvailable =  items.stream().allMatch(item -> !item.isPickedUp());
        }
        return isAvailable;
    }

    @Override
    public List<Appointment> SortByDate(boolean descending, List<Appointment> appointments) throws Exception {
        Comparator<Appointment> comparator = Comparator.comparing(appointment -> appointment.getStartTime());
        if (descending) {
            comparator = comparator.reversed();
        }
        Collections.sort(appointments, comparator);
        return appointments;
    }

}
