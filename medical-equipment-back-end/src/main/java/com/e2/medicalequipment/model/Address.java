package com.e2.medicalequipment.model;

import com.e2.medicalequipment.dto.CreateAddressDTO;
import jakarta.persistence.*;

import static jakarta.persistence.InheritanceType.TABLE_PER_CLASS;

@Entity
@Table(schema = "stakeholders", name = "addresses")
@Inheritance(strategy=TABLE_PER_CLASS)
public class Address {
    @Id
    @SequenceGenerator(name = "addressSeq", sequenceName = "addressSeq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "addressSeq")
    private Long id;
    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    public Address(){

    }
    public Address(Long id, String street, String city, String country) {
        this.id = id;
        this.street = street;
        this.city = city;
        this.country = country;
    }

    public Address(CreateAddressDTO dto) {
        this.street = dto.street;
        this.city = dto.city;
        this.country = dto.country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
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
}




