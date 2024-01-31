package com.e2.medicalequipment.service;

import com.e2.medicalequipment.dto.CreateAppointmentDTO;
import com.e2.medicalequipment.model.Appointment;
import com.e2.medicalequipment.model.CompanyAdministrator;

import java.util.List;
import java.util.Optional;

public interface AppointmentService {
    Appointment Create(CreateAppointmentDTO appointmentDTO) throws Exception;
    boolean Delete(Long id) throws Exception;
    Appointment CreateIrregular(CreateAppointmentDTO appointmentDTO, String customerId);
    List<Appointment> GetAll() throws Exception;
/*
    List<Appointment> GetByCompanyId(Long companyId) throws Exception;
    List<Appointment> GetAvailableByCompanyId(Long companyId) throws Exception;
    List<Appointment> GetReservedByCompanyId(Long companyId) throws Exception;
*/
    List<Appointment> GetFreeByCompanyId(Long companyId) throws Exception;
    List<Appointment> GetScheduledByCompanyId(Long companyId) throws Exception;
    Appointment GetById(Long id) throws Exception;
    boolean CheckReservation(Long id) throws Exception;

    List<Appointment> GetScheduledByCustomerId(Long customerId) throws Exception;

    Appointment FindById(Long id) throws Exception;

    List<Appointment> GetPickedUpByCustomerId(Long customerId) throws Exception;
    List<CompanyAdministrator> getFreeAdminsForAppointment(String startTime);
}
