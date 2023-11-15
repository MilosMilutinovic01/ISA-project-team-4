package com.e2.medicalequipment.repository;

import com.e2.medicalequipment.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
