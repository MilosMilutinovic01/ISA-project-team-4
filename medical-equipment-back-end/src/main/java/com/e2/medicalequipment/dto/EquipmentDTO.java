package com.e2.medicalequipment.dto;

import com.e2.medicalequipment.model.Equipment;
import com.e2.medicalequipment.model.EquipmentType;
import jakarta.persistence.Column;

public class EquipmentDTO {

    public Long id;
    public String description;
    public EquipmentType type;
    public Double price;

    public EquipmentDTO(Equipment equipment) {
        this.id = equipment.getId();
        this.description = equipment.getDescription();
        this.type = equipment.getType();
        this.price = equipment.getPrice();
    }

}
