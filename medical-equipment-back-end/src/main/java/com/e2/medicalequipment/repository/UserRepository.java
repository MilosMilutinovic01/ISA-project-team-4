package com.e2.medicalequipment.repository;

import com.e2.medicalequipment.model.Customer;
import com.e2.medicalequipment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long> {
}
