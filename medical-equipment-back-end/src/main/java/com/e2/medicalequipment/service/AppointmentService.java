package com.e2.medicalequipment.service;

import com.e2.medicalequipment.dto.CreateAppointmentDTO;
import com.e2.medicalequipment.model.Appointment;

import java.util.List;

public interface AppointmentService {
    Appointment Create(CreateAppointmentDTO appointmentDTO) throws Exception;
    List<Appointment> GetAll() throws Exception;
    List<Appointment> GetByCompanyId(Long companyId) throws Exception;
}
