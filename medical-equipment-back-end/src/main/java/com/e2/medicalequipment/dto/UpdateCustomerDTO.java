package com.e2.medicalequipment.dto;

import com.e2.medicalequipment.model.Address;
import com.e2.medicalequipment.model.CustomerCategory;

public class UpdateCustomerDTO {
    public Long id;
    public String name;
    public String lastname;
    public String username;
    public Address address;
    public String phoneNumber;
    public String profession;
    public Long penaltyPoints;
    public String password;
    public CustomerCategory category;

    public UpdateCustomerDTO() {
    }
}