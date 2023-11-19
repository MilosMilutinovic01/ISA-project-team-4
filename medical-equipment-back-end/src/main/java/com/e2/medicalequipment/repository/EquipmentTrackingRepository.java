package com.e2.medicalequipment.repository;

import com.e2.medicalequipment.model.Company;
import com.e2.medicalequipment.model.EquipmentTracking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentTrackingRepository  extends JpaRepository<EquipmentTracking, Long> {
}

