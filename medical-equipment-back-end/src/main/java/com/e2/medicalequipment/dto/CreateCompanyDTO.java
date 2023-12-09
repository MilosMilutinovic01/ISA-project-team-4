package com.e2.medicalequipment.dto;

import com.e2.medicalequipment.model.Company;
import javax.persistence.*;
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
