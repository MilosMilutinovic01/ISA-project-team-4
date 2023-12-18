package com.e2.medicalequipment.dto;

import com.e2.medicalequipment.model.Address;
import com.e2.medicalequipment.model.Appointment;
import com.e2.medicalequipment.model.CompanyAdministrator;

import java.time.LocalDateTime;

public class CreateAppointmentDTO {
    public String startTime;
    public String endTime;
    public  UpdateCompanyAdministratorDTO companyAdministrator;

    public CreateAppointmentDTO() {
    }
}
