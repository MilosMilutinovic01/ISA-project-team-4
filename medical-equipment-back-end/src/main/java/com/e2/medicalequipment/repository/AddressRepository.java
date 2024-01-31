package com.e2.medicalequipment.repository;

import com.e2.medicalequipment.model.Address;
import com.e2.medicalequipment.model.Customer;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

public interface AddressRepository  extends JpaRepository<Address, Long> {
    @Lock(LockModeType.PESSIMISTIC_READ)
    public Address save(Address address);
}
