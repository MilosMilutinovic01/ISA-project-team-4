package com.e2.medicalequipment.dto;

import com.e2.medicalequipment.model.Appointment;

public class ItemDTO {
    public Long id;
    public int count;
    public AppointmentDTO appointment;
    public EquipmentDTO equipment;

    public ItemDTO(Long id, int count, AppointmentDTO appointment, EquipmentDTO equipment) {
        this.id = id;
        this.count = count;
        this.appointment = appointment;
        this.equipment = equipment;
    }
}
