package com.e2.medicalequipment.model;

import com.e2.medicalequipment.dto.CreateCompanyDTO;
import com.e2.medicalequipment.dto.UpdateCompanyDTO;
import jakarta.persistence.*;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;



@Entity
@Table(schema = "stakeholders", name = "companies")
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

    //slobodni termini

    public Company() {
    }

    public Company(Long id, String name, Address address, LocalTime startTime, LocalTime endTime, String description) {
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
    public Company(UpdateCompanyDTO updateCompanyDTO) {
        this.id = updateCompanyDTO.id;
        this.name = updateCompanyDTO.name;
        this.address = new Address(updateCompanyDTO.address);

       this.startTime = LocalTime.parse(updateCompanyDTO.startTime);
       this.endTime = LocalTime.parse(updateCompanyDTO.endTime);
        this.description = updateCompanyDTO.description;
        this.averageRating = updateCompanyDTO.averageRating;
    }

    public Company(Company company) {
        this.id = company.id;
        this.name = company.name;
        this.address = company.address;
        this.startTime = company.startTime;
        this.endTime = company.endTime;
        this.description = company.description;
        this.averageRating = company.averageRating;
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
