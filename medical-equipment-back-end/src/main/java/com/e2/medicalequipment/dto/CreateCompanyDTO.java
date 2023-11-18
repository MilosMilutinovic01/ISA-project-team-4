package com.e2.medicalequipment.dto;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import com.e2.medicalequipment.dto.CreateAddressDTO;

public class CreateCompanyDTO {

    public String name;
    public CreateAddressDTO address;
    public String startTime;
    public String endTime;
    public String description;
    public CreateCompanyDTO() {
    }
}
