package com.e2.medicalequipment.model;

import com.e2.medicalequipment.dto.CreateCompanyDTO;

import jakarta.persistence.*;

import java.time.LocalDateTime;

import static jakarta.persistence.InheritanceType.TABLE_PER_CLASS;

@Entity
@Table(schema = "stakeholders", name = "companies")
@Inheritance(strategy=TABLE_PER_CLASS)
public class Company {
    @Id
    @SequenceGenerator(name = "companySeq", sequenceName = "companySeq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "companySeq")
    private Long id;
    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;
    @Column(name = "city")
    private String city;
    @Column(name = "country")
    private String country;

    @Column(name = "startTime")
    private LocalDateTime startTime;

    @Column(name = "endTime")
    private LocalDateTime endTime;

    @Column(name = "description")
    private String description;

    @Column(name = "averageRating")
    private double averageRating;

    //lista opreme

    //slobodni termini
    public Company() {
    }

    public Company(Long id, String name, String address, String city, String country, LocalDateTime startTime, LocalDateTime endTime, String description, double averageRating) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.country = country;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.averageRating = averageRating;
    }
    public Company(CreateCompanyDTO createCompanyDTO) {
        this.name = createCompanyDTO.name;
        this.address = createCompanyDTO.address;
        this.city = createCompanyDTO.city;
        this.country = createCompanyDTO.country;
        this.description = createCompanyDTO.description;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }
}
