package com.e2.medicalequipment.repository;

import com.e2.medicalequipment.model.Customer;
import com.e2.medicalequipment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Modifying
    @Transactional
    @Query("UPDATE Customer c SET c.penaltyPoints = c.penaltyPoints + :points WHERE c.id = :userId")
    void changePenaltyPoints(Long userId, Integer points);

    @Modifying
    @Transactional
    @Query("UPDATE Customer c SET c.penaltyPoints = :points")
    void deleteAllPenaltyPoints(Integer points);
}
