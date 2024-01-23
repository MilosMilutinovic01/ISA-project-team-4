package com.e2.medicalequipment.service;

import com.e2.medicalequipment.dto.CreateAppointmentDTO;
import com.e2.medicalequipment.model.Appointment;

import java.util.List;

public interface AppointmentService {
    Appointment Create(CreateAppointmentDTO appointmentDTO) throws Exception;
    Appointment CreateIrregular(CreateAppointmentDTO appointmentDTO) throws Exception;
    List<Appointment> GetAll() throws Exception;
    List<Appointment> GetFreeByCompanyId(Long companyId) throws Exception;
    List<Appointment> GetScheduledByCompanyId(Long companyId) throws Exception;
}
