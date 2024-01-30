package com.e2.medicalequipment.controller;

import com.e2.medicalequipment.dto.CreateCustomerDTO;
import com.e2.medicalequipment.dto.JwtAuthenticationRequest;
import com.e2.medicalequipment.dto.UserTokenState;
import com.e2.medicalequipment.model.Customer;
import com.e2.medicalequipment.model.User;
import com.e2.medicalequipment.service.CustomerServiceImpl;
import com.e2.medicalequipment.service.EmailService;
import com.e2.medicalequipment.service.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthenticationController {
    @Autowired
    private CustomerServiceImpl customerService;

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/verify/{id}")
    public Boolean verifyUser(@PathVariable String id) throws Exception {
        Customer existCustomer = this.customerService.findByVerificationToken(id);
        if(existCustomer == null)
            throw new RuntimeException();
        userService.changeUserStatus(userService.getUserById(existCustomer.getId()));
        return true;
    }
    @PostMapping("/customer/register")
    public ResponseEntity<Customer> addUser(@RequestBody CreateCustomerDTO customer, UriComponentsBuilder ucBuilder) throws Exception {
        Optional<User> existUser = this.userService.findByUsername(customer.username);
        if (existUser.isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        Customer savedCustomer = this.customerService.Create(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    @PostMapping(value = "login", consumes = "application/json")
    public ResponseEntity<UserTokenState> login(@RequestBody JwtAuthenticationRequest loginDto, HttpServletRequest request){
        return ResponseEntity.ok(userService.login(loginDto));
    }
}
