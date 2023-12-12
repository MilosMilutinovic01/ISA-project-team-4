package com.e2.medicalequipment.repository;

import com.e2.medicalequipment.model.Customer;
import com.e2.medicalequipment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
