package com.e2.medicalequipment.dto;

public class CreateCustomerDTO {
    public String username;

    public String password;

    public String email;

    public String address;

    public String name;

    public String lastname;

    public String city;

    public String country;

    public String phoneNumber;

    public String profession;

    public CreateCustomerDTO() {
    }

    public CreateCustomerDTO(String username, String password, String email, String address, String name, String lastname, String city, String country, String phoneNumber, String profession) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
        this.name = name;
        this.lastname = lastname;
        this.city = city;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.profession = profession;
    }
}
