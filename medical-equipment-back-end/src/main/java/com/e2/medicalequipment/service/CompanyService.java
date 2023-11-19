package com.e2.medicalequipment.service;

import com.e2.medicalequipment.dto.CreateCompanyDTO;
import com.e2.medicalequipment.dto.UpdateCompanyDTO;
import com.e2.medicalequipment.model.Company;

import java.util.ArrayList;
import java.util.List;

public interface CompanyService {
    Company Create(CreateCompanyDTO c) throws Exception;

    List<Company> GetAll() throws Exception;
    List<Company> Search(String name,
                         String street,
                         String city,
                         String country) throws Exception;

    List<Company> Filter(String rate,
                         List<Company> companies) throws Exception;

    Company Get(String id) throws Exception;
    Company Update(UpdateCompanyDTO companyDTO) throws Exception;

}


