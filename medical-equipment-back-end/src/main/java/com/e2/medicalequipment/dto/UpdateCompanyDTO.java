package com.e2.medicalequipment.dto;

import com.e2.medicalequipment.model.Company;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class UpdateCompanyDTO {

    public Long id;
    public String name;
    public String address;
    public String city;
    public String country;
    public String startTime;
    public String endTime;
    public String description;
    public Double averageRating;

    public UpdateCompanyDTO() {
    }
}
