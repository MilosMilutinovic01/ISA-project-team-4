package com.e2.medicalequipment.repository;

import com.e2.medicalequipment.model.Appointment;
import com.e2.medicalequipment.model.Item;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT a FROM Appointment a WHERE a.id = :id")
    Appointment findAppointmentById(long id);

    @Query("SELECT a FROM Appointment a WHERE a.companyAdministrator.companyId = :company_id")
    List<Appointment> findAllByCompanyId(@Param("company_id") String companyId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT a FROM Appointment a WHERE a.startTime = :start_time")
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
    List<Appointment> findAllByStartTime(@Param("start_time") LocalDateTime startTime);

    @Lock(LockModeType.PESSIMISTIC_READ)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
    public Appointment save(Appointment appointment);

}
