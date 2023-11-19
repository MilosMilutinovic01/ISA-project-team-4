package com.e2.medicalequipment.dto;

import com.e2.medicalequipment.dto.UpdateCompanyDTO;
import com.e2.medicalequipment.dto.EquipmentDTO;

import com.e2.medicalequipment.model.Equipment;
import com.e2.medicalequipment.model.EquipmentTracking;
import jakarta.persistence.*;

public class EquipmentTrackingDTO {

    public Long id;
    public int count;

    public UpdateCompanyDTO company;
    public EquipmentDTO equipment;

    public EquipmentTrackingDTO(EquipmentTracking equipmentTracking) {
        EquipmentDTO equipment = new EquipmentDTO(equipmentTracking.getEquipment());
        UpdateCompanyDTO companyDTO = new UpdateCompanyDTO(equipmentTracking.getCompany());
        this.id = equipmentTracking.getId();
        this.count = equipmentTracking.getCount();
        this.company = companyDTO;
        this.equipment = equipment;
    }
}
