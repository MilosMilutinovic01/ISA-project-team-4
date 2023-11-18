package com.e2.medicalequipment.dto;

import com.e2.medicalequipment.model.Company;

public class UpdateCompanyDTO {

    public String id;
    public String name;
    public String address;
    public String city;
    public String country;
    public String startTime;
    public String endTime;
    public String description;
    public String averageRating;

    public UpdateCompanyDTO() {
    }

    public UpdateCompanyDTO(Company company) {
        this(company.getId().toString(), company.getName(), company.getAddress(), company.getCity(), company.getCountry(), company.getStartTime().toString(), company.getEndTime().toString(), company.getDescription(), String.valueOf(company.getAverageRating()));
    }

    public UpdateCompanyDTO(String id, String name, String address, String city, String country, String startTime, String endTime, String description, String averageRating) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.country = country;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.averageRating = averageRating;
    }
}
