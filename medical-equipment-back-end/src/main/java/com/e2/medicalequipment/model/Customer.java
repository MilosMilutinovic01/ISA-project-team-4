package com.e2.medicalequipment.model;

import com.e2.medicalequipment.dto.CreateCustomerDTO;
import com.e2.medicalequipment.dto.UpdateCustomerDTO;
import jakarta.persistence.*;

@Entity
@Table(schema = "stakeholders", name = "customers")
@PrimaryKeyJoinColumn(name = "cutomer_id")
public class Customer extends User{
    @Column(name = "profession")
    private String profession;

    @Column(name = "penaltyPoints")
    private Long penaltyPoints;

    @Column(name = "category")
    private CustomerCategory category;

    public Customer() {

    }

    public Customer(Long id, String name, String lastname, String email, Address address, String phoneNumber, String password, UserType userType, String profession, Long penaltyPoints, CustomerCategory category, Boolean enabled) {
        super(id,
                name,
                lastname,
                email,
                address,
                phoneNumber,
                password,
                userType,
                enabled);
        this.profession = profession;
        this.penaltyPoints = penaltyPoints;
        this.category = category;
    }

    public Customer(CreateCustomerDTO createCustomerDTO) {
        super(null,
                createCustomerDTO.name,
                createCustomerDTO.lastname,
                createCustomerDTO.email,
                createCustomerDTO.address,
                createCustomerDTO.phoneNumber,
                createCustomerDTO.password);
        this.profession = createCustomerDTO.profession;
    }
    public Customer(UpdateCustomerDTO updateCustomerDTO) {
        super(updateCustomerDTO.id,
                updateCustomerDTO.name,
                updateCustomerDTO.lastname,
                updateCustomerDTO.email,
                updateCustomerDTO.address,
                updateCustomerDTO.phoneNumber,
                updateCustomerDTO.password);
        this.profession = updateCustomerDTO.profession;
        this.penaltyPoints = updateCustomerDTO.penaltyPoints;
        this.category = updateCustomerDTO.category;
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

    public CustomerCategory getCategory() {
        return category;
    }

    public void setCategory(CustomerCategory category) {
        this.category = category;
    }
}