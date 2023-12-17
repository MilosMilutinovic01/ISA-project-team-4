package com.e2.medicalequipment.repository;

import com.e2.medicalequipment.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

}
