package com.e2.medicalequipment.service;

import com.e2.medicalequipment.dto.CreateAppointmentDTO;
import com.e2.medicalequipment.model.Appointment;
import com.e2.medicalequipment.model.CompanyAdministrator;
import com.e2.medicalequipment.repository.AppointmentRepository;
import com.e2.medicalequipment.repository.CompanyAdministratorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class AppointmentServiceImpl implements AppointmentService{
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd yyyy HH:mm:ss 'GMT'Z (zzzz)", Locale.ENGLISH);


    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private CompanyAdministratorRepository companyAdministratorRepository;

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
    public List<Appointment> GetByCompanyId(Long companyId) throws Exception {
        List<Appointment> allAppointments = new ArrayList<>();
        for(Appointment a : appointmentRepository.findAll()) {
            if(a.getCompanyAdministrator().getCompanyId() == companyId){
                allAppointments.add(a);
            }
        }
        return allAppointments;
    }
}
