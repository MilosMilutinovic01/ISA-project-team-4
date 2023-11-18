package com.e2.medicalequipment.service;

import com.e2.medicalequipment.dto.CreateCustomerDTO;
import com.e2.medicalequipment.dto.UpdateCustomerDTO;
import com.e2.medicalequipment.model.Customer;
import com.e2.medicalequipment.model.CustomerCategory;
import com.e2.medicalequipment.model.UserType;
import com.e2.medicalequipment.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer Create(CreateCustomerDTO createCustomerDto) throws Exception {
        Customer customer = new Customer(createCustomerDto);
        customer.setUserType(UserType.CUSTOMER);
        customer.setPenaltyPoints(0L);
        customer.setCategory(CustomerCategory.REGULAR);

        if (customer.getId() != null) {
            throw new Exception("ID must be null for a new entity.");
        }

        // Save the Test entity using JpaRepository
        Customer savedCustomer = customerRepository.save(customer);

        return savedCustomer;
    }

    @Override
    public Customer Update(UpdateCustomerDTO customerDTO) throws Exception {
        Customer customer = new Customer(customerDTO);
        customer.setUserType(UserType.CUSTOMER);

        if (customer.getId() == null) {
            throw new Exception("ID must not be null for updating entity.");
        }
        Customer savedCustomer = customerRepository.save(customer);

        return savedCustomer;
    }

    @Override
    public Customer Get(String id) throws Exception {
        return this.customerRepository.findById(Long.parseLong(id)).get();
    }
}
