package com.e2.medicalequipment.model;

import jakarta.persistence.*;

@Entity
@Table(schema = "stakeholders", name = "systemAdministrators")
@PrimaryKeyJoinColumn(name = "system_administrator_id")
public class SystemAdministrator extends User{
    public SystemAdministrator(){

    }
    public SystemAdministrator(Long id, String name, String lastname, String email, Address address, String phoneNumber, String password, UserType userType, Boolean enabled) {
        super(id,
                name,
                lastname,
                email,
                address,
                phoneNumber,
                password,
                userType,
                enabled);
    }
}