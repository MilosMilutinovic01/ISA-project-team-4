package com.e2.medicalequipment.service;

import com.e2.medicalequipment.dto.CreateCustomerDTO;
import com.e2.medicalequipment.model.Customer;
import com.e2.medicalequipment.model.User;
import com.e2.medicalequipment.model.UserType;
import com.e2.medicalequipment.repository.CustomerRepository;
import com.e2.medicalequipment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Customer Create(CreateCustomerDTO createCustomerDto) throws Exception {
        User user = new User();
        user.setUsername(createCustomerDto.email);
        user.setPassword(createCustomerDto.password);
        user.setUserType(UserType.CUSTOMER);
        user.setId(null);

        Customer customer = new Customer(createCustomerDto);
        customer.setUser(user);

        if (customer.getId() != null && user.getId() != null) {
            throw new Exception("ID must be null for a new entity.");
        }

        // Save the Test entity using JpaRepository
        Customer savedCustomer = customerRepository.save(customer);
        User savedUser = userRepository.save(user);

        return savedCustomer;
    }
}
