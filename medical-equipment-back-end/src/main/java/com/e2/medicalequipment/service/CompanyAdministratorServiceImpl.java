package com.e2.medicalequipment.service;

import com.e2.medicalequipment.dto.CompanyAdministratorDTO;
import com.e2.medicalequipment.model.CompanyAdministrator;
import com.e2.medicalequipment.model.UserType;
import com.e2.medicalequipment.repository.CompanyAdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyAdministratorServiceImpl implements CompanyAdministratorService {
    @Autowired
    private CompanyAdministratorRepository companyAdministratorRepository;

    @Override
    public CompanyAdministrator Create(CompanyAdministratorDTO createCompanyAdministratorDto) throws Exception {
        CompanyAdministrator companyAdministrator = new CompanyAdministrator(createCompanyAdministratorDto);
        companyAdministrator.setUserType(UserType.COMPANY_ADMINISTRATOR);

        if (companyAdministrator.getId() != null) {
            throw new Exception("ID must be null for a new entity.");
        }

        // Save the Test entity using JpaRepository
        CompanyAdministrator savedCompanyAdministrator = companyAdministratorRepository.save(companyAdministrator);

        return savedCompanyAdministrator;
    }
}
