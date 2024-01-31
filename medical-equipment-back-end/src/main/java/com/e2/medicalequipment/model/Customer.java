package com.e2.medicalequipment.model;

import com.e2.medicalequipment.dto.CreateCustomerDTO;
import com.e2.medicalequipment.dto.UpdateCustomerDTO;
import jakarta.persistence.*;

@Entity
@Table(schema = "stakeholders", name = "customers")
@PrimaryKeyJoinColumn(name = "customer_id")
public class Customer extends User{
    @Column(name = "profession")
    private String profession;

    @Column(name = "penaltyPoints")
    private Long penaltyPoints;

    @Column(name = "category")
    private CustomerCategory category;

    @Column(name = "verificationToken")
    private String verificationToken;

    public Customer() {

    }

    public Customer(Long id, String name, String lastname, String username, Address address, String phoneNumber, String password, Role role, String profession, Long penaltyPoints, CustomerCategory category, Boolean enabled) {
        super(id,
                name,
                lastname,
                username,
                address,
                phoneNumber,
                password,
                role,
                enabled);
        this.profession = profession;
        this.penaltyPoints = penaltyPoints;
        this.category = category;
    }

    public Customer(CreateCustomerDTO createCustomerDTO) {
        super(null,
                createCustomerDTO.name,
                createCustomerDTO.lastname,
                createCustomerDTO.username,
                createCustomerDTO.address,
                createCustomerDTO.phoneNumber,
                createCustomerDTO.password);
        this.profession = createCustomerDTO.profession;
    }
    public Customer(UpdateCustomerDTO updateCustomerDTO) {
        super(updateCustomerDTO.id,
                updateCustomerDTO.name,
                updateCustomerDTO.lastname,
                updateCustomerDTO.username,
                updateCustomerDTO.address,
                updateCustomerDTO.phoneNumber,
                updateCustomerDTO.password);
        this.profession = updateCustomerDTO.profession;
        this.penaltyPoints = updateCustomerDTO.penaltyPoints;
        this.category = updateCustomerDTO.category;
    }

    public Customer(Customer customer) {
        super(customer);
        this.profession = customer.profession;
        this.penaltyPoints = customer.penaltyPoints;
        this.category = customer.category;
        this.verificationToken = customer.verificationToken;
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

    public String getVerificationToken() { return verificationToken; }

    public void setVerificationToken(String verificationToken) { this.verificationToken = verificationToken; }
}