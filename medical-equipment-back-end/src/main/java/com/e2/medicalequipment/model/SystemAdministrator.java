package com.e2.medicalequipment.model;

import javax.persistence.*;

@Entity
@Table(schema = "stakeholders", name = "systemAdministrators")
public class SystemAdministrator extends User{
    public SystemAdministrator(){

    }
    public SystemAdministrator(Long id, String name, String lastname, String email, Address address, String phoneNumber, String password) {
        super(id,
                name,
                lastname,
                email,
                address,
                phoneNumber,
                password);
    }
}