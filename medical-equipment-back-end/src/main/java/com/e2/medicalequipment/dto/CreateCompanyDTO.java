package com.e2.medicalequipment.dto;

import com.e2.medicalequipment.model.Company;
import jakarta.persistence.*;

import java.time.LocalDateTime;

public class CreateCompanyDTO {

    public String name;
    public String address;
    public String city;
    public String country;
    public String startTime;
    public String endTime;
    public String description;
    public CreateCompanyDTO() {
    }
}
