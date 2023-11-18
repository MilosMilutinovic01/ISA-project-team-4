package com.e2.medicalequipment.dto;

import com.e2.medicalequipment.model.Company;
import com.e2.medicalequipment.dto.UpdateAddressDTO;
import org.hibernate.sql.Update;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class UpdateCompanyDTO {

    public Long id;
    public String name;

    public UpdateAddressDTO address;
    public String startTime;
    public String endTime;
    public String description;
    public Double averageRating;
    public UpdateCompanyDTO() {
    }
    public UpdateCompanyDTO(Company company) {
        UpdateAddressDTO addresDto = new UpdateAddressDTO(company.getAddress());
        this.id = company.getId();
        this.name = company.getName();
        this.address = addresDto;
        this.startTime = company.getStartTime().toString();
        this.endTime = company.getEndTime().toString();
        this.description = company.getDescription();
        this.averageRating = company.getAverageRating();;
    }
}
