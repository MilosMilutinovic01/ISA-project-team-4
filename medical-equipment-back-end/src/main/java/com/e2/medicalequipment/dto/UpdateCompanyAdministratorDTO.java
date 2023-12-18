package com.e2.medicalequipment.dto;

import com.e2.medicalequipment.model.Address;
import com.e2.medicalequipment.model.CompanyAdministrator;

public class UpdateCompanyAdministratorDTO {
    public Long id;
    public String password;
    public String username;

    public Address address;

    public String name;

    public String lastname;

    public String phoneNumber;
    public long companyId;

    public UpdateCompanyAdministratorDTO() {
    }

    public UpdateCompanyAdministratorDTO(CompanyAdministrator companyAdministrator) {
        this.id=companyAdministrator.getId();
        this.password = companyAdministrator.getPassword();
        this.username = companyAdministrator.getUsername();
        this.address = companyAdministrator.getAddress();
        this.name = companyAdministrator.getName();
        this.lastname = companyAdministrator.getLastname();
        this.phoneNumber = companyAdministrator.getPhoneNumber();
        this.companyId = companyAdministrator.getCompanyId();
    }
}
