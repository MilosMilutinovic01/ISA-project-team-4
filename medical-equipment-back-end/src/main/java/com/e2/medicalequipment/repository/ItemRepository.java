package com.e2.medicalequipment.repository;

import com.e2.medicalequipment.model.Company;
import com.e2.medicalequipment.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
