package com.e2.medicalequipment.service;

import com.e2.medicalequipment.dto.CreateAppointmentDTO;
import com.e2.medicalequipment.model.Appointment;
import com.e2.medicalequipment.model.CompanyAdministrator;
import com.e2.medicalequipment.model.Item;
import com.e2.medicalequipment.repository.AppointmentRepository;
import com.e2.medicalequipment.repository.CompanyAdministratorRepository;
import com.e2.medicalequipment.repository.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

@Service
public class AppointmentServiceImpl implements AppointmentService{
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
        CompanyAdministrator ca = companyAdministratorRepository.findById(createAppointmentDto.companyAdministrator.id).orElseThrow(() -> new EntityNotFoundException("Company admin not found"));
        appointment.setCompanyAdministrator(ca);

        if (appointment.getId() != null) {
            throw new Exception("ID must be null for a new entity.");
        }
        Appointment savedAppointment = appointmentRepository.save(appointment);
        return savedAppointment;
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
        for(Appointment a : appointmentRepository.findAll()) {
            allAppointments.add(a);
        }
        return allAppointments;
    }
    @Override
    public List<Appointment> GetFreeByCompanyId(Long companyId) throws Exception{
        List<Appointment> freeAppointments = new ArrayList<>();
        for(Appointment a : appointmentRepository.findAllByCompanyId(companyId.toString())) {
            if (itemRepository.findAllByAppointmentId(a.getId().toString()).isEmpty()) {
                freeAppointments.add(a);
            }
        }
        return freeAppointments;
    }
    @Override
    public List<Appointment> GetScheduledByCompanyId(Long companyId) throws Exception{
        List<Appointment> scheduledAppointments = new ArrayList<>();
        for(Appointment a : appointmentRepository.findAllByCompanyId(companyId.toString())) {
            if (!itemRepository.findAllByAppointmentId(a.getId().toString()).isEmpty()) {
                scheduledAppointments.add(a);
            }
        }
        return scheduledAppointments;
    }
/*
    @Override
    public List<Appointment> GetAvailableByCompanyId(Long companyId) throws Exception {
        List<Appointment> companyAppointments = new ArrayList<>();
        List<Appointment> availableAppointments = new ArrayList<>();
        for(Appointment a : appointmentRepository.findAll()) {
            if(a.getCompanyAdministrator().getCompanyId() == companyId){
                companyAppointments.add(a);
            }
        }


        boolean isAvailable;
        for(Appointment a : companyAppointments) {
            isAvailable = true;
            for(Item i : itemRepository.findAllByCompanyId(String.valueOf(companyId))){
                if(i.getAppointment() != null) {
                    if (i.getAppointment().getId() == a.getId()) {
                        isAvailable = false;
                        break;
                    }
                }
            }
            if(isAvailable) {
                availableAppointments.add(a);
            }
        }
        return availableAppointments;
    }

    @Override
    public List<Appointment> GetReservedByCompanyId(Long companyId) throws Exception {
        List<Appointment> companyAppointments = new ArrayList<>();
        List<Appointment> reservedAppointments = new ArrayList<>();
        for(Appointment a : appointmentRepository.findAll()) {
            if(a.getCompanyAdministrator().getCompanyId() == companyId){
                companyAppointments.add(a);
            }
        }

        for(Item i : itemRepository.findAllByCompanyId(String.valueOf(companyId))){
            for(Appointment a : companyAppointments) {
                if (i.getAppointment() != null) {
                    if (i.getAppointment().getId() == a.getId()) {
                        reservedAppointments.add(a);
                        break;
                    }
                }
            }
        }
        return reservedAppointments;
    }*/
}
