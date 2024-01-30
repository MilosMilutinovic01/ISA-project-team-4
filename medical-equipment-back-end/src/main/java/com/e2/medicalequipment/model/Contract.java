package com.e2.medicalequipment.model;

import com.e2.medicalequipment.dto.ContractDTO;
import com.e2.medicalequipment.dto.EquipmentTrackingDTO;
import jakarta.persistence.*;

@Entity
@Table(schema = "stakeholders", name = "contracts")
public class Contract {

    @Id
    @Column(name = "hospital")
    private String hospital;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "equipment_id", referencedColumnName = "id")
    private Equipment equipment;
    @Column(name = "count")
    private int count;
    @Column(name = "date_in_month")
    private int dateInMonth;

    public Contract() {

    }
    public Contract(String hospital, Equipment equipment, int count, int dateInMonth) {
        this.hospital = hospital;
        this.equipment = equipment;
        this.count = count;
        this.dateInMonth = dateInMonth;
    }

    public Contract(ContractDTO dto) {
        this.hospital = dto.hospital;
        this.count = dto.count;
        this.dateInMonth = dto.dateInMonth;
    }
    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getDateInMonth() {
        return dateInMonth;
    }

    public void setDateInMonth(int dateInMonth) {
        this.dateInMonth = dateInMonth;
    }

}
