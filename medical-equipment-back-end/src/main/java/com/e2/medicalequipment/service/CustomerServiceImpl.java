package com.e2.medicalequipment.service;

import com.e2.medicalequipment.dto.CreateCustomerDTO;
import com.e2.medicalequipment.dto.UpdateCustomerDTO;
import com.e2.medicalequipment.model.Customer;
import com.e2.medicalequipment.model.CustomerCategory;
import com.e2.medicalequipment.model.Role;
import com.e2.medicalequipment.repository.CustomerRepository;
import com.e2.medicalequipment.security.AuthTokenFilter;
import com.e2.medicalequipment.security.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

    @Override
    public Customer Create(CreateCustomerDTO createCustomerDto) throws Exception {
        Customer customer = new Customer(createCustomerDto);
        customer.setPassword(passwordEncoder.encode(createCustomerDto.password));
        customer.setRole(Role.CUSTOMER);
        customer.setPenaltyPoints(0L);
        customer.setCategory(CustomerCategory.REGULAR);
        customer.setEnabled(false);
        customer.setUsername(createCustomerDto.username);
        String verificationToken = UUID.randomUUID().toString().replaceAll("-", "");
        customer.setVerificationToken(verificationToken);

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
        customer.setRole(Role.CUSTOMER);

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

    @Override
    public Customer findByVerificationToken(String token) throws Exception {
        return customerRepository.findByVerificationToken(token);
    }
}
