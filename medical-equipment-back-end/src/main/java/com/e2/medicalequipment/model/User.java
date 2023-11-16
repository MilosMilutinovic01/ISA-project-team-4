package com.e2.medicalequipment.model;

import jakarta.persistence.*;

import static jakarta.persistence.InheritanceType.TABLE_PER_CLASS;

@Entity
@Inheritance(strategy=TABLE_PER_CLASS)
public class User {
    @Id
    @SequenceGenerator(name = "userSeq", sequenceName = "userSeq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSeq")
    private Long id;
    @Column(name = "name")
    private String name;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "address")
    private String address;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "password")
    private String password;

    @Column(name = "userType")
    private UserType userType;

    public User() {
    }

    public User(Long id, String name, String lastname, String email, String city, String country, String address, String phoneNumber, String password, UserType userType) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.city = city;
        this.country = country;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.userType = userType;
    }

    public User(Long id, String name, String lastname, String email, String city, String country, String address, String phoneNumber, String password) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.city = city;
        this.country = country;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
