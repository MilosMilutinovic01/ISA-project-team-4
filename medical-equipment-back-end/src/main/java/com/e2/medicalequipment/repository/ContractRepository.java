package com.e2.medicalequipment.repository;

import com.e2.medicalequipment.model.Contract;
import com.e2.medicalequipment.model.EquipmentTracking;
import com.e2.medicalequipment.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContractRepository  extends JpaRepository<Contract, Long> {
    @Query("SELECT c FROM Contract c WHERE c.hospital = :hospital")
    Contract findByHospital(@Param("hospital") String hospital);
}
