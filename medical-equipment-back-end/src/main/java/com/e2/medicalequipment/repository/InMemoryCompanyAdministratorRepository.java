package com.e2.medicalequipment.repository;

import com.e2.medicalequipment.model.CompanyAdministrator;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryCompanyAdministratorRepository {
    private static AtomicLong counter = new AtomicLong();
    private final ConcurrentMap<Long, CompanyAdministrator> companyAdministrators = new ConcurrentHashMap<Long, CompanyAdministrator>();

    public CompanyAdministrator Create(CompanyAdministrator companyAdministrator) {
        Long id = companyAdministrator.getId();

        if (id == null) {
            id = counter.incrementAndGet();
            companyAdministrator.setId(id);
        }

        this.companyAdministrators.put(id, companyAdministrator);

        return companyAdministrator;
    }
}
