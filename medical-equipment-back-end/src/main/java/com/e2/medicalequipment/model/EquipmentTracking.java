package com.e2.medicalequipment.model;
import jakarta.persistence.*;

@Entity
@Table(schema = "stakeholders", name = "equipmentTracking")
public class EquipmentTracking {
    @Id
    @SequenceGenerator(name = "equipmentTrackingSeq", sequenceName = "equipmentTrackingSeq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "equipmentTrackingSeq")
    private Long id;
    @Column(name = "count")
    private int count;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "equipment_id", referencedColumnName = "id")
    private Equipment equipment;


    public EquipmentTracking() {

    }

    public EquipmentTracking(Long id, int count, Company company, Equipment equipment) {
        this.id = id;
        this.count = count;
        this.company = company;
        this.equipment = equipment;
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }
}

