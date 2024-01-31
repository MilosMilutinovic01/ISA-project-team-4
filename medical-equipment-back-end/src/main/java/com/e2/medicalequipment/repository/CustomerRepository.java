package com.e2.medicalequipment.repository;

import com.e2.medicalequipment.model.Customer;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT c FROM Customer c WHERE c.verificationToken = :token")
    Customer findByVerificationToken(@Param("token") String token);

    @Lock(LockModeType.PESSIMISTIC_READ)
    public Customer save(Customer customer);
}
