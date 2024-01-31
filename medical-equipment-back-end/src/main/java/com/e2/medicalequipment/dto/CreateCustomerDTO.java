package com.e2.medicalequipment.dto;

import com.e2.medicalequipment.model.Address;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToOne;

public class CreateCustomerDTO {
    public String password;

    public String username;

    public Address address;

    public String name;

    public String lastname;

    public String phoneNumber;

    public String profession;

    public CreateCustomerDTO() {
    }

    public CreateCustomerDTO(String password, String username, Address address,
                             String name, String lastname, String phoneNumber, String profession) {
        this.password = password;
        this.username = username;
        this.address = address;
        this.name = name;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
        this.profession = profession;
    }
}