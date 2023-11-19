package com.e2.medicalequipment.dto;

import com.e2.medicalequipment.model.CompanyAdministrator;

public class CompanyAdministratorDTO {
    public String password;
    public String email;

    public String address;

    public String name;

    public String lastname;

    public String city;

    public String country;

    public String phoneNumber;
    public long companyId;

    public CompanyAdministratorDTO() {
    }

    public CompanyAdministratorDTO(CompanyAdministrator companyAdministrator) {
        this.password = companyAdministrator.getPassword();
        this.email = companyAdministrator.getEmail();
        this.address = companyAdministrator.getAddress();
        this.name = companyAdministrator.getName();
        this.lastname = companyAdministrator.getLastname();
        this.city = companyAdministrator.getCity();
        this.country = companyAdministrator.getCountry();
        this.phoneNumber = companyAdministrator.getPhoneNumber();
        this.companyId = companyAdministrator.getCompanyId();
    }
}
