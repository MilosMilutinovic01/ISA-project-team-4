package com.e2.medicalequipment.service;

import com.e2.medicalequipment.dto.CompanyAdministratorDTO;
import com.e2.medicalequipment.model.CompanyAdministrator;

public interface CompanyAdministratorService {
    CompanyAdministrator Create(CompanyAdministratorDTO test) throws Exception;
    CompanyAdministrator Get(String id) throws Exception;
}
