package com.e2.medicalequipment.repository;

import com.e2.medicalequipment.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentTrackingRepository  extends JpaRepository<Company, Long> {
}

