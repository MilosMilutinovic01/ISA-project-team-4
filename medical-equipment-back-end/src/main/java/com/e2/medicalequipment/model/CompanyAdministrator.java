package com.e2.medicalequipment.model;

import com.e2.medicalequipment.dto.CompanyAdministratorDTO;
import jakarta.persistence.*;

@Entity
@Table(schema = "stakeholders", name = "companyAdministrators")
public class CompanyAdministrator extends User{

    @Column(name = "companyId")
    private Long companyId;
/*
    @Column(name = "appointments")
    private Long[] appointments;
*/
    public CompanyAdministrator() {

    }

    public CompanyAdministrator(Long id, String name, String lastname, String email, String city, String country, String address, String phoneNumber, String password, UserType userType, Long companyId) {
        super(id,
                name,
                lastname,
                email,
                city,
                country,
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
                companyAdministratorDTO.city,
                companyAdministratorDTO.country,
                companyAdministratorDTO.address,
                companyAdministratorDTO.phoneNumber,
                companyAdministratorDTO.password);
    }
    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

}
