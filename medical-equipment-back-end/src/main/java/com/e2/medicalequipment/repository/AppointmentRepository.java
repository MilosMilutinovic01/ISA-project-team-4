package com.e2.medicalequipment.repository;

import com.e2.medicalequipment.model.Appointment;
import com.e2.medicalequipment.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Appointment findById(long id);

    @Query("SELECT a FROM Appointment a WHERE a.companyAdministrator.companyId = :company_id")
    List<Appointment> findAllByCompanyId(@Param("company_id") String companyId);
}
