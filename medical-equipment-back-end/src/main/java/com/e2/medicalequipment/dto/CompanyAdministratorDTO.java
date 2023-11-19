package com.e2.medicalequipment.dto;

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
}