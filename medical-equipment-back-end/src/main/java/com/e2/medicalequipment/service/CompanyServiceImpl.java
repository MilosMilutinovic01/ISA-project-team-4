package com.e2.medicalequipment.service;

import com.e2.medicalequipment.dto.CreateCompanyDTO;
import com.e2.medicalequipment.model.Company;
import com.e2.medicalequipment.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
public class CompanyServiceImpl implements CompanyService {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public Company Create(CreateCompanyDTO createCompanyDto) throws Exception {
        Company company = new Company(createCompanyDto);
        company.setAverageRating(0L);
        LocalTime startTime = LocalTime.parse(createCompanyDto.startTime, formatter);
        company.setStartTime(startTime);
        LocalTime endTime = LocalTime.parse(createCompanyDto.endTime, formatter);
        company.setEndTime(endTime);

        if (company.getId() != null) {
            throw new Exception("ID must be null for a new entity.");
        }

        Company savedCompany = companyRepository.save(company);

        return savedCompany;
    }
}
