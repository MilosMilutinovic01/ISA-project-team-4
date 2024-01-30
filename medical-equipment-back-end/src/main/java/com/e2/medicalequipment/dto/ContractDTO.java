package com.e2.medicalequipment.dto;

import com.e2.medicalequipment.model.Contract;
import com.e2.medicalequipment.model.EquipmentTracking;

public class ContractDTO {
    public String hospital;
    public String equipment;
    public int count;
    public int dateInMonth;

    public ContractDTO() {
    }

    public ContractDTO(Contract contract) {
        this.hospital = contract.getHospital();
        this.equipment = contract.getEquipment().getName();
        this.count = contract.getCount();
        this.dateInMonth = contract.getDateInMonth();
    }
}
