package com.e2.medicalequipment.dto;

import com.e2.medicalequipment.model.Contract;
import com.e2.medicalequipment.model.EquipmentTracking;

public class UpdateContractDTO {
    public String hospital;
    public String equipment;
    public int count;
    public int dateInMonth;
    public boolean cancelledThisMonth;

    public UpdateContractDTO() {
    }
}
