package com.e2.medicalequipment.model;

import com.e2.medicalequipment.dto.ItemDTO;
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "appointment_id", referencedColumnName = "id")
    private Appointment appointment;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "equipment_id", referencedColumnName = "id")
    private Equipment equipment;

    public Item() {

    }

    public Item(Long id, int count, Appointment appointment, Equipment equipment) {
        this.id = id;
        this.count = count;
        this.appointment = appointment;
        this.equipment = equipment;
    }

    public Item(ItemDTO itemDto){
        this.count = itemDto.count;
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
}
