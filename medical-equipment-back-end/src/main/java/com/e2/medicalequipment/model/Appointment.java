package com.e2.medicalequipment.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(schema = "stakeholders", name = "appointments")
public class Appointment {
    @Id
    @SequenceGenerator(name = "appointmentSeq", sequenceName = "appointmentSeq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appointmentSeq")
    private Long id;

    @Column(name = "startTime")
    private LocalDateTime startTime;

    @Column(name = "endTime")
    private LocalDateTime endTime;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_administrator_id", referencedColumnName = "company_administrator_id")
    private CompanyAdministrator companyAdministrator;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;

    public Appointment() {

    }

    public Appointment(Long id, LocalDateTime start, LocalDateTime end, CompanyAdministrator companyAdministrator, Customer customer) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.companyAdministrator = companyAdministrator;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public CompanyAdministrator getCompanyAdministrator() {
        return companyAdministrator;
    }

    public void setCompanyAdministrator(CompanyAdministrator companyAdministrator) {
        this.companyAdministrator = companyAdministrator;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
