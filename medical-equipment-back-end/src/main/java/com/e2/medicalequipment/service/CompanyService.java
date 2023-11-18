package com.e2.medicalequipment.service;

import com.e2.medicalequipment.dto.CreateCompanyDTO;
import com.e2.medicalequipment.model.Company;

public interface CompanyService {
    Company Create(CreateCompanyDTO c) throws Exception;
    Company findOne(String id) throws Exception;
    }
