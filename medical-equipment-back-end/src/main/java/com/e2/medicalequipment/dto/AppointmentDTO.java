package com.e2.medicalequipment.dto;

import com.e2.medicalequipment.model.CompanyAdministrator;
import com.e2.medicalequipment.model.Customer;

public class AppointmentDTO {

    public Long id;
    public String startTime;
    public String endTime;
    public UpdateCompanyAdministratorDTO companyAdministrator;

    /*public AppointmentDTO(Long id, String start, String end, CompanyAdministrator companyAdministrator, Customer customer) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.companyAdministrator = companyAdministrator;
        this.customer = customer;
    }*/
}
