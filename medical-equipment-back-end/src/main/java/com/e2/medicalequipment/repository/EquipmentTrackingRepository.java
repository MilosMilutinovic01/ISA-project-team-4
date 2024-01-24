package com.e2.medicalequipment.repository;

import com.e2.medicalequipment.model.Company;
import com.e2.medicalequipment.model.EquipmentTracking;
import com.e2.medicalequipment.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EquipmentTrackingRepository  extends JpaRepository<EquipmentTracking, Long> {
    @Query("SELECT e FROM EquipmentTracking e WHERE e.equipment.id = :equipment_id")
    List<EquipmentTracking> findAllByEquipmentId(@Param("equipment_id") String equipmentId);
    @Query("SELECT e FROM EquipmentTracking e WHERE e.company.id = :company_id AND e.equipment.id = :equipment_id")
    EquipmentTracking findByCompanyAndEquipmentId(
            @Param("company_id") long companyId,
            @Param("equipment_id") long equipmentId
    );

}

