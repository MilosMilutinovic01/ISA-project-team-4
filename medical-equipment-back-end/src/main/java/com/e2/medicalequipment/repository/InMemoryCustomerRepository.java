package com.e2.medicalequipment.repository;

import com.e2.medicalequipment.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryCustomerRepository {
    private static AtomicLong counter = new AtomicLong();
    private final ConcurrentMap<Long, Customer> customers = new ConcurrentHashMap<Long, Customer>();

    public Customer Create(Customer customer) {
        Long id = customer.getId();

        if (id == null) {
            id = counter.incrementAndGet();
            customer.setId(id);
        }

        this.customers.put(id, customer);

        return customer;
    }
}
