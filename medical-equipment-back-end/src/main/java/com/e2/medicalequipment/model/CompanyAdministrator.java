package com.e2.medicalequipment.model;

import com.e2.medicalequipment.dto.CompanyAdministratorDTO;
import com.e2.medicalequipment.dto.UpdateCompanyAdministratorDTO;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(schema = "stakeholders", name = "companyAdministrators")
@PrimaryKeyJoinColumn(name = "company_administrator_id")
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class CompanyAdministrator extends User implements Serializable {

    @Column(name = "company_id")
    private Long companyId;

    /*
        @Column(name = "appointments")
        private Long[] appointments;
    */
    public CompanyAdministrator() {

    }

    public CompanyAdministrator(Long id, String name, String lastname, String email, Address address, String phoneNumber, String password, Role role, Long companyId, Boolean enabled) {
        super(id,
                name,
                lastname,
                email,
                address,
                phoneNumber,
                password,
                role,
                true);
        this.companyId = companyId;
    }

    public CompanyAdministrator(CompanyAdministratorDTO companyAdministratorDTO) {
        super(null,
                companyAdministratorDTO.name,
                companyAdministratorDTO.lastname,
                companyAdministratorDTO.username,
                companyAdministratorDTO.address,
                companyAdministratorDTO.phoneNumber,
                companyAdministratorDTO.password,
                Role.COMPANY_ADMINISTRATOR,
                true);
        this.companyId = companyAdministratorDTO.companyId;
    }

    public CompanyAdministrator(UpdateCompanyAdministratorDTO companyAdministratorDTO) {
        super(companyAdministratorDTO.id,
                companyAdministratorDTO.name,
                companyAdministratorDTO.lastname,
                companyAdministratorDTO.username,
                companyAdministratorDTO.address,
                companyAdministratorDTO.phoneNumber,
                companyAdministratorDTO.password,
                Role.COMPANY_ADMINISTRATOR,
                true);
        this.companyId = companyAdministratorDTO.companyId;

    }

    public CompanyAdministrator(CompanyAdministrator companyAdministrator) {
        this.companyId = companyAdministrator.companyId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}