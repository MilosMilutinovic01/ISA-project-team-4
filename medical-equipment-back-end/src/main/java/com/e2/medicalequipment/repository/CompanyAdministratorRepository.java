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
    @Query("SELECT a FROM CompanyAdministrator a WHERE a.companyId = :company_id")
    List<CompanyAdministrator> findAllByCompanyId(@Param("company_id") String companyId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select ca from CompanyAdministrator ca where ca.id = :id")
    @QueryHints({@QueryHint(name = "jakarta.persistence.lock.timeout", value ="0")})
    CompanyAdministrator findOneById(@Param("id")Long id);
}
