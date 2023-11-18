package com.e2.medicalequipment.service;

import com.e2.medicalequipment.dto.CreateCompanyDTO;
import com.e2.medicalequipment.dto.UpdateCompanyDTO;
import com.e2.medicalequipment.model.Company;

public interface CompanyService {
    Company Create(CreateCompanyDTO c) throws Exception;
    Company Get(String id) throws Exception;
    Company Update(UpdateCompanyDTO companyDTO) throws Exception;
    }
