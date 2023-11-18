package com.e2.medicalequipment.service;

import com.e2.medicalequipment.dto.CreateCompanyDTO;
import com.e2.medicalequipment.model.Company;

import java.util.ArrayList;
import java.util.List;

public interface CompanyService {
    Company Create(CreateCompanyDTO c) throws Exception;
    List<Company> GetAll() throws Exception;
}