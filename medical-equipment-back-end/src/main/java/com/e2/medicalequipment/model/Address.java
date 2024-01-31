package com.e2.medicalequipment.model;

import com.e2.medicalequipment.dto.CreateAddressDTO;
import com.e2.medicalequipment.dto.UpdateAddressDTO;
import jakarta.persistence.*;


@Entity
@Table(schema = "stakeholders", name = "addresses")
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

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "latitude")
    private double latitude;

    public Address(){

    }
    public Address(Long id, String street, String city, String country, double longitude, double latitude) {
        this.id = id;
        this.street = street;
        this.city = city;
        this.country = country;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Address(CreateAddressDTO dto) {
        this.street = dto.street;
        this.city = dto.city;
        this.country = dto.country;
        this.latitude = dto.lat;
        this.longitude = dto.lng;
    }

    public Address(UpdateAddressDTO dto) {
        this.id = dto.id;
        this.street = dto.street;
        this.city = dto.city;
        this.country = dto.country;
        this.latitude = dto.lat;
        this.longitude = dto.lng;
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

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}