package com.e2.medicalequipment.service;

import com.e2.medicalequipment.dto.CreateCompanyDTO;
import com.e2.medicalequipment.model.Company;
import com.e2.medicalequipment.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

@Service
public class CompanyServiceImpl implements CompanyService {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public Company Create(CreateCompanyDTO createCompanyDto) throws Exception {
        Company company = new Company(createCompanyDto);
        company.setAverageRating(-1L);
       // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startTime = LocalDateTime.parse(createCompanyDto.startTime, formatter);
        company.setStartTime(startTime);
        LocalDateTime endTime = LocalDateTime.parse(createCompanyDto.endTime, formatter);
        company.setEndTime(endTime);

        if (company.getId() != null) {
            throw new Exception("ID must be null for a new entity.");
        }

        Company savedCompany = companyRepository.save(company);

        return savedCompany;
    }

    public Company findOne(String id) {
        return companyRepository.findById(Long.valueOf(id)).get();
    }
}
