package com.e2.medicalequipment.repository;

import com.e2.medicalequipment.model.Appointment;
import com.e2.medicalequipment.model.Company;
import com.e2.medicalequipment.model.Item;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query("SELECT i FROM Item i WHERE i.customer.id = :customer_id")
    List<Item> findAllByCustomerId(@Param("customer_id") String customerId);

    @Query("SELECT i FROM Item i WHERE i.appointment.id = :appointment_id")
    List<Item> findAllByAppointmentId(@Param("appointment_id") String appointmentId);

    @Query("SELECT i FROM Item i WHERE i.company.id = :company_id")
    List<Item> findAllByCompanyId(@Param("company_id") String companyId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Item i WHERE i.id = :id")
    void deleteItemById(Long id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
    Item findById(long id);

    @Lock(LockModeType.PESSIMISTIC_READ)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
    public Item save(Item item);

}
