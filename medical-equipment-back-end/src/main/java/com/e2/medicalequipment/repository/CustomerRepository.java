package com.e2.medicalequipment.repository;

import com.e2.medicalequipment.model.Customer;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT c FROM Customer c WHERE c.verificationToken = :token")
    Customer findByVerificationToken(@Param("token") String token);

    @Lock(LockModeType.PESSIMISTIC_READ)
    @QueryHints({@QueryHint(name = "jakarta.persistence.lock.timeout", value ="0")})
    public Customer save(Customer customer);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select c from Customer c where c.username = :username")
    @QueryHints({@QueryHint(name = "jakarta.persistence.lock.timeout", value ="0")})
    Customer findByUsername(@Param("username")String username);

}
