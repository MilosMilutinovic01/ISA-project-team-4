package com.e2.medicalequipment.repository;

import com.e2.medicalequipment.model.Contract;
import com.e2.medicalequipment.model.Customer;
import com.e2.medicalequipment.model.Equipment;
import com.e2.medicalequipment.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EquipmentRepository  extends JpaRepository<Equipment, Long> {
    @Query("SELECT e FROM Equipment e WHERE e.name = :name")
    Equipment findByName(@Param("name") String equipmentName);
}
