package com.e2.medicalequipment.model;

import com.e2.medicalequipment.dto.CreateCompanyDTO;

import jakarta.persistence.*;

import java.time.LocalTime;

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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @Column(name = "startTime")
    private LocalTime startTime;

    @Column(name = "endTime")
    private LocalTime endTime;

    @Column(name = "description")
    private String description;

    @Column(name = "averageRating")
    private double averageRating;

    //lista opreme

    //slobodni termini

    public Company() {
    }

    public Company(Long id, String name, Address address, LocalTime startTime, LocalTime endTime, String description, double averageRating) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.averageRating = averageRating;
    }
    public Company(CreateCompanyDTO createCompanyDTO) {
        this.name = createCompanyDTO.name;
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

    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
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
