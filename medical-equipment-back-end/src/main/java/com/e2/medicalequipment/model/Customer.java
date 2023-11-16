package com.e2.medicalequipment.model;

import com.e2.medicalequipment.dto.CreateCustomerDTO;
import jakarta.persistence.*;

@Entity
@Table(schema = "stakeholders", name = "customers")
public class Customer extends User{
    @Column(name = "profession")
    private String profession;

    @Column(name = "penaltyPoints")
    private Long penaltyPoints;

    public Customer() {

    }

    public Customer(Long id, String name, String lastname, String email, String city, String country, String address, String phoneNumber, String password, UserType userType, String profession, Long penaltyPoints) {
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
        this.profession = profession;
        this.penaltyPoints = penaltyPoints;
    }

    public Customer(CreateCustomerDTO createCustomerDTO) {
        super(null,
                createCustomerDTO.name,
                createCustomerDTO.lastname,
                createCustomerDTO.email,
                createCustomerDTO.city,
                createCustomerDTO.country,
                createCustomerDTO.address,
                createCustomerDTO.phoneNumber,
                createCustomerDTO.password);
        this.profession = createCustomerDTO.profession;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Long getPenaltyPoints() {
        return penaltyPoints;
    }

    public void setPenaltyPoints(Long penaltyPoints) {
        this.penaltyPoints = penaltyPoints;
    }

}
