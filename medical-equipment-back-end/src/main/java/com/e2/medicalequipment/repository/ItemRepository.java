package com.e2.medicalequipment.repository;

import com.e2.medicalequipment.model.Company;
import com.e2.medicalequipment.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query("SELECT i FROM Item i WHERE i.customer.id = :customer_id")
    List<Item> findAllByCustomerId(@Param("customer_id") String customerId);
}
