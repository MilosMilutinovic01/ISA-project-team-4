package com.e2.medicalequipment.model;

import com.e2.medicalequipment.dto.CompanyAdministratorDTO;
import com.e2.medicalequipment.dto.UpdateCompanyAdministratorDTO;
import jakarta.persistence.*;

@Entity
@Table(schema = "stakeholders", name = "companyAdministrators")
public class CompanyAdministrator extends User{

    @Column(name = "company_id")
    private Long companyId;

    /*
        @Column(name = "appointments")
        private Long[] appointments;
    */
    public CompanyAdministrator() {

    }

    public CompanyAdministrator(Long id, String name, String lastname, String email, Address address, String phoneNumber, String password, UserType userType, Long companyId) {
        super(id,
                name,
                lastname,
                email,
                address,
                phoneNumber,
                password,
                userType);
        this.companyId = companyId;
    }

    public CompanyAdministrator(CompanyAdministratorDTO companyAdministratorDTO) {
        super(null,
                companyAdministratorDTO.name,
                companyAdministratorDTO.lastname,
                companyAdministratorDTO.email,
                companyAdministratorDTO.address,
                companyAdministratorDTO.phoneNumber,
                companyAdministratorDTO.password);
        this.companyId = companyAdministratorDTO.companyId;
    }

    public CompanyAdministrator(UpdateCompanyAdministratorDTO companyAdministratorDTO) {
        super(companyAdministratorDTO.id,
                companyAdministratorDTO.name,
                companyAdministratorDTO.lastname,
                companyAdministratorDTO.email,
                companyAdministratorDTO.address,
                companyAdministratorDTO.phoneNumber,
                companyAdministratorDTO.password);
        this.companyId = companyAdministratorDTO.companyId;

    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}