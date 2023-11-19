package com.e2.medicalequipment.service;


import com.e2.medicalequipment.model.EquipmentTracking;

import java.util.List;
import java.util.Set;

public interface EquipmentTrackingService {
    List<EquipmentTracking> GetAll() throws Exception;
}
