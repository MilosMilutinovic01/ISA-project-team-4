package com.e2.medicalequipment.service;

import com.e2.medicalequipment.dto.CompanyAdministratorDTO;
import com.e2.medicalequipment.dto.UpdateCompanyAdministratorDTO;
import com.e2.medicalequipment.model.*;
import com.e2.medicalequipment.repository.CompanyAdministratorRepository;
import com.e2.medicalequipment.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyAdministratorServiceImpl implements CompanyAdministratorService {
    @Autowired
    private CompanyAdministratorRepository companyAdministratorRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public CompanyAdministrator Create(CompanyAdministratorDTO createCompanyAdministratorDto) throws Exception {
        CompanyAdministrator companyAdministrator = new CompanyAdministrator(createCompanyAdministratorDto);
        companyAdministrator.setRole(Role.COMPANY_ADMINISTRATOR);
        companyAdministrator.setPassword(passwordEncoder.encode("ca"));
        if (companyAdministrator.getId() != null) {
            throw new Exception("ID must be null for a new entity.");
        }

        // Save the Test entity using JpaRepository
        CompanyAdministrator savedCompanyAdministrator = companyAdministratorRepository.save(companyAdministrator);

        return savedCompanyAdministrator;
    }

    @Override
    public CompanyAdministrator Get(String id) throws Exception{
        return this.companyAdministratorRepository.findById(Long.parseLong(id)).get();
    }

    public CompanyAdministrator Update(UpdateCompanyAdministratorDTO companyAdministratorDTO) throws Exception{
        CompanyAdministrator companyAdministrator = new CompanyAdministrator(companyAdministratorDTO);
        if ((companyAdministrator.getId() == null)){
            throw new Exception("ID must not be null for updating entity.");
        }
        companyAdministrator.setRole(Role.COMPANY_ADMINISTRATOR);
        //companyAdministrator.setAddress(address);
        CompanyAdministrator savedCompanyAdministrator = companyAdministratorRepository.save(companyAdministrator);
        return savedCompanyAdministrator;
    }

    @Override
    public List<CompanyAdministrator> GetAll() throws Exception {
        List<CompanyAdministrator> allAdmins = new ArrayList<>();
        for(CompanyAdministrator e : companyAdministratorRepository.findAll()) {
            allAdmins.add(e);
        }
        return allAdmins;
    }

    @Override
    //@Transactional(readOnly = true)
    public List<CompanyAdministrator> GetAllByCompanyId(String companyId) throws Exception {
        List<CompanyAdministrator> admins = new ArrayList<>();
        for(CompanyAdministrator a : companyAdministratorRepository.findAllByCompanyId(companyId)) {
            admins.add(a);
        }
        return admins;
    }



}
