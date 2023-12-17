package com.e2.medicalequipment.service;

import com.e2.medicalequipment.dto.SystemAdministratorDTO;
import com.e2.medicalequipment.model.SystemAdministrator;
import java.util.List;

public interface SystemAdministratorService {
    SystemAdministrator Create(SystemAdministratorDTO test) throws Exception;
    SystemAdministrator Get(Long id) throws Exception;
    SystemAdministrator Update(SystemAdministratorDTO systemAdministratorDTO) throws Exception;
}
