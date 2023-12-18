package com.e2.medicalequipment.model;

import com.e2.medicalequipment.dto.CreateItemDTO;
import jakarta.persistence.*;

@Entity
@Table(schema = "stakeholders", name = "items")
public class Item {
    @Id
    @SequenceGenerator(name = "itemSeq", sequenceName = "itemSeq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itemSeq")
    private Long id;

    @Column(name = "count")
    private int count;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "appointment_id", referencedColumnName = "id")
    private Appointment appointment;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "company_id")
    private Company company;

    public Item() {

    }

    public Item(Long id, int count, Appointment appointment, Equipment equipment, Customer customer, Company company) {
        this.id = id;
        this.count = count;
        this.appointment = appointment;
        this.equipment = equipment;
        this.customer = customer;
        this.company = company;
    }

    public Item(CreateItemDTO itemDto){
        this.count = itemDto.count;
    }

    public Item(Item item) {
        this.id = item.id;
        this.count = item.count;

        if (item.appointment != null) {
            this.appointment = new Appointment(item.appointment);
        }

        if (item.equipment != null) {
            this.equipment = new Equipment(item.equipment);
        }

        if (item.customer != null) {
            this.customer = new Customer(item.customer);
        }

        if (item.company != null) {
            this.company = new Company(item.company);
        }
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
