package com.e2.medicalequipment.dto;

import com.e2.medicalequipment.model.CompanyAdministrator;
import com.e2.medicalequipment.model.Address;

public class CompanyAdministratorDTO {
    public String password;
    public String email;

    public Address address;

    public String name;

    public String lastname;

    public String phoneNumber;
    public long companyId;

    public CompanyAdministratorDTO() {
    }

    public CompanyAdministratorDTO(CompanyAdministrator companyAdministrator) {
        this.password = companyAdministrator.getPassword();
        this.email = companyAdministrator.getUsername();
        this.address = companyAdministrator.getAddress();
        this.name = companyAdministrator.getFirstName();
        this.lastname = companyAdministrator.getLastName();
        this.phoneNumber = companyAdministrator.getPhoneNumber();
        this.companyId = companyAdministrator.getCompanyId();
    }
}
