package com.e2.medicalequipment.model;

import com.e2.medicalequipment.dto.SystemAdministratorDTO;
import jakarta.persistence.*;

@Entity
@Table(schema = "stakeholders", name = "systemAdministrators")
@PrimaryKeyJoinColumn(name = "system_administrator_id")
public class SystemAdministrator extends User{

    @Column(name = "hasLoggedBefore")
    private Boolean hasLoggedBefore;
    public SystemAdministrator(){

    }
    public SystemAdministrator(Long id, String name, String lastname, String email, Address address, String phoneNumber, String password, Role role, Boolean enabled, Boolean hasLoggedBefore) {
        super(id,
                name,
                lastname,
                email,
                address,
                phoneNumber,
                password,
                role,
                enabled);
        this.hasLoggedBefore = hasLoggedBefore;
    }
    public SystemAdministrator(SystemAdministratorDTO systemAdministratorDTO) {
        super(systemAdministratorDTO.id,
                systemAdministratorDTO.name,
                systemAdministratorDTO.lastname,
                systemAdministratorDTO.username,
                systemAdministratorDTO.address,
                systemAdministratorDTO.phoneNumber,
                systemAdministratorDTO.password,
                Role.SYSTEM_ADMINISTRATOR,
                true);
        this.hasLoggedBefore = systemAdministratorDTO.hasLoggedBefore;
    }
    public Boolean getHasLoggedBefore() {
        return hasLoggedBefore;
    }

    public void setHasLoggedBefore(Boolean hasLoggedBefore) {
        this.hasLoggedBefore = hasLoggedBefore;
    }
}