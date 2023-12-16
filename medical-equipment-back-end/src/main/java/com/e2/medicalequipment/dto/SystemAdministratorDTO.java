package com.e2.medicalequipment.dto;

import com.e2.medicalequipment.model.SystemAdministrator;
import com.e2.medicalequipment.model.Address;

public class SystemAdministratorDTO {
    public Long id;

    public String password;
    public String email;

    public Address address;

    public String name;

    public String lastname;

    public String phoneNumber;
    public Boolean hasLoggedBefore;

    public SystemAdministratorDTO() {
    }

    public SystemAdministratorDTO(SystemAdministrator systemAdministrator) {
        this.id = systemAdministrator.getId();
        this.password = systemAdministrator.getPassword();
        this.email = systemAdministrator.getUsername();
        this.address = systemAdministrator.getAddress();
        this.name = systemAdministrator.getName();
        this.lastname = systemAdministrator.getLastname();
        this.phoneNumber = systemAdministrator.getPhoneNumber();
        this.hasLoggedBefore = systemAdministrator.getHasLoggedBefore();
    }
}

