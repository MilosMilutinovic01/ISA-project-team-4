package com.e2.medicalequipment.dto;

import com.e2.medicalequipment.model.CustomerCategory;
import com.e2.medicalequipment.model.UserType;

public class UpdateCustomerDTO {
    public Long id;
    public String name;
    public String lastname;
    public String email;
    public String address;
    public String city;
    public String country;
    public String phoneNumber;
    public String profession;
    public Long penaltyPoints;
    public String password;
    public CustomerCategory category;

    public UpdateCustomerDTO() {
    }
}
