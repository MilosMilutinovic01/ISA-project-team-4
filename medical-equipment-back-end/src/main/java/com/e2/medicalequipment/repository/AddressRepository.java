package com.e2.medicalequipment.repository;

import com.e2.medicalequipment.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository  extends JpaRepository<Address, Long> {
}
