package com.e2.medicalequipment.service;

import com.e2.medicalequipment.dto.CreateAppointmentDTO;
import com.e2.medicalequipment.model.Appointment;
import com.e2.medicalequipment.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService{
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm");

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public Appointment Create(CreateAppointmentDTO createAppointmentDto) throws Exception {
        Appointment appointment = new Appointment(createAppointmentDto);
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
}
