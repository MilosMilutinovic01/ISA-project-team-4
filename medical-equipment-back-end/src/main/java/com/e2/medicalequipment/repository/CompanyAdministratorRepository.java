package com.e2.medicalequipment.repository;

import com.e2.medicalequipment.model.CompanyAdministrator;
import com.e2.medicalequipment.model.Customer;
import com.e2.medicalequipment.model.Item;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompanyAdministratorRepository extends JpaRepository<CompanyAdministrator, Long> {
    //@Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT a FROM CompanyAdministrator a WHERE a.companyId = :company_id")
    //@QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
    List<CompanyAdministrator> findAllByCompanyId(@Param("company_id") String companyId);

}
