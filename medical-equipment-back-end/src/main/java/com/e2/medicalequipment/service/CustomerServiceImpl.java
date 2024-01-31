package com.e2.medicalequipment.service;

import com.e2.medicalequipment.dto.CreateAddressDTO;
import com.e2.medicalequipment.dto.CreateCustomerDTO;
import com.e2.medicalequipment.dto.UpdateCustomerDTO;
import com.e2.medicalequipment.model.Address;
import com.e2.medicalequipment.model.Customer;
import com.e2.medicalequipment.model.CustomerCategory;
import com.e2.medicalequipment.model.EquipmentTracking;
import com.e2.medicalequipment.model.Role;
import com.e2.medicalequipment.repository.AddressRepository;
import com.e2.medicalequipment.repository.CustomerRepository;
import com.e2.medicalequipment.security.AuthTokenFilter;
import com.e2.medicalequipment.security.JwtUtils;
import jakarta.persistence.PessimisticLockException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.List;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private EmailService emailService;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

    @Override
    @Transactional(readOnly = false)
    public Customer Create(CreateCustomerDTO createCustomerDto) {
        try{
            addressRepository.save(createCustomerDto.address);
            Customer customer = customerRepository.findByUsername(createCustomerDto.username);
            if(customer != null){
                throw new IllegalArgumentException("Username already exists.");
            }
            customer = new Customer(createCustomerDto);
            customer.setPassword(passwordEncoder.encode(createCustomerDto.password));
            customer.setRole(Role.CUSTOMER);
            customer.setPenaltyPoints(0L);
            customer.setCategory(CustomerCategory.REGULAR);
            customer.setEnabled(false);
            customer.setUsername(createCustomerDto.username);
            String verificationToken = UUID.randomUUID().toString().replaceAll("-", "");
            customer.setVerificationToken(verificationToken);

            Customer savedCustomer = customerRepository.save(customer);

            String verificationLink = "http://localhost:4200/api/auth/verify/id=" + savedCustomer.getVerificationToken();
            String verificationMail = generateVerificationEmail(savedCustomer.getName(), verificationLink);

            emailService.sendNotificaitionAsync(savedCustomer.getUsername(), "Mejl za potvrdu registracije ISA-team-34", verificationMail);
            System.out.println("Email poslat valjda...");
            return savedCustomer;
        }catch (PessimisticLockingFailureException e){
            throw e;
        }
    }

    private String generateVerificationEmail(String name, String verificationLink) {
        return String.format("<p>Dear <strong>" + name + "</strong>,</p>\n" +
                        "<p>Thank you for choosing ISA! We're excited to have you on board.</p>\n" +
                        "<p>Your registration is almost complete. Please click the following link to activate your account:</p>\n" +
                        "<p><a href=" + verificationLink + ">Activation Link</a></p>\n" +
                        "<p>If you have any questions, feel free to contact our support team.</p>\n" +
                        "<p>Best regards,<br/>The ISA Team</p>"
                , name, verificationLink);
    }

    @Transactional(readOnly = false)
    public Customer findByUsername(String username) {
        Customer c = customerRepository.findByUsername(username);
        return c;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @Override
    public Customer Update(UpdateCustomerDTO customerDTO) throws Exception {
        Customer customer = new Customer(customerDTO);
        customer.setRole(Role.CUSTOMER);
        customer.setEnabled(true);

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

    @Override
    public Customer GivePenaltyPoints(Long id) throws Exception {
        Customer customer = this.customerRepository.findById(id).get();
        customer.setPenaltyPoints(2L);
        if (customer.getId() == null) {
            throw new Exception("ID must not be null for updating entity.");
        }
        Customer savedCustomer = customerRepository.save(customer);
        return savedCustomer;
    }
}
