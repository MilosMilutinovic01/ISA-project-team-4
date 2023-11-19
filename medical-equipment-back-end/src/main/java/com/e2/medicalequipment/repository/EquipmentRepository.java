package com.e2.medicalequipment.repository;

import com.e2.medicalequipment.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepository  extends JpaRepository<Equipment, Long> {
}
