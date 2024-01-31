package com.e2.medicalequipment.service;

import com.e2.medicalequipment.dto.CompanyAdministratorDTO;
import com.e2.medicalequipment.dto.EquipmentTrackingDTO;
import com.e2.medicalequipment.dto.UpdateCompanyAdministratorDTO;
import com.e2.medicalequipment.model.CompanyAdministrator;

import java.util.List;

public interface CompanyAdministratorService {
    CompanyAdministrator Create(CompanyAdministratorDTO test) throws Exception;
    CompanyAdministrator Get(String id) throws Exception;
    CompanyAdministrator Update(UpdateCompanyAdministratorDTO companyAdministratorDTO) throws Exception;
    List<CompanyAdministrator> GetAll() throws Exception;

    List<CompanyAdministrator> GetAllByCompanyId(String companyId) throws Exception;
}
