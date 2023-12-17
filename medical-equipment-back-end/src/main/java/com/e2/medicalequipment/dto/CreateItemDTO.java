package com.e2.medicalequipment.dto;

import com.e2.medicalequipment.model.Appointment;
import com.e2.medicalequipment.model.Equipment;

public class CreateItemDTO {
    public int count;
    public EquipmentDTO equipment;

    /*public ItemDTO(Long id, int count, Appointment appointment, Equipment equipment) {
        this.id = id;
        this.count = count;
        this.appointment = appointment;
        this.equipment = equipment;
    }*/
}
