package com.e2.medicalequipment.dto;

import com.e2.medicalequipment.model.Appointment;
import com.e2.medicalequipment.model.Equipment;

public class CreateItemDTO {
    public int count;
    public Equipment equipment;
    public long customerId;

    public long companyId;

    /*public ItemDTO(Long id, int count, Appointment appointment, Equipment equipment) {
        this.id = id;
        this.count = count;
        this.appointment = appointment;
        this.equipment = equipment;
    }*/
}
