package com.e2.medicalequipment.service;

import com.e2.medicalequipment.dto.SystemAdministratorDTO;
import com.e2.medicalequipment.model.*;
import com.e2.medicalequipment.repository.SystemAdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SystemAdministratorServiceImpl implements SystemAdministratorService{
    @Autowired
    private SystemAdministratorRepository systemAdministratorRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public SystemAdministrator Create(SystemAdministratorDTO systemAdministratorDto) throws Exception {
        SystemAdministrator systemAdministrator = new SystemAdministrator(systemAdministratorDto);
        systemAdministrator.setRole(Role.SYSTEM_ADMINISTRATOR);
        systemAdministrator.setPassword(passwordEncoder.encode("sa"));
        if (systemAdministrator.getId() != null) {
            throw new Exception("ID must be null for a new entity.");
        }

        // Save the Test entity using JpaRepository
        SystemAdministrator savedSystemAdministrator = systemAdministratorRepository.save(systemAdministrator);

        return savedSystemAdministrator;
    }

    @Override
    public SystemAdministrator Get(Long id) throws Exception{
        return this.systemAdministratorRepository.findById(id).get();
    }

    public SystemAdministrator Update(SystemAdministratorDTO systemAdministratorDTO) throws Exception{
        String encodedPassword = passwordEncoder.encode(systemAdministratorDTO.password);
        SystemAdministrator systemAdministrator = new SystemAdministrator(systemAdministratorDTO);
        systemAdministrator.setPassword(encodedPassword);
        if ((systemAdministrator.getId() == null)){
            throw new Exception("ID must not be null for updating entity.");
        }
        systemAdministrator.setRole(Role.SYSTEM_ADMINISTRATOR);
        //companyAdministrator.setAddress(address);
        SystemAdministrator savedSystemAdministrator = systemAdministratorRepository.save(systemAdministrator);
        return savedSystemAdministrator;
    }
}
