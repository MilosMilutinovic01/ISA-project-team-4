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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    private EmailService emailService;

    @PostMapping("/customer/register")
    public ResponseEntity<User> addUser(@RequestBody CreateCustomerDTO customer, UriComponentsBuilder ucBuilder) throws Exception {
        Optional<User> existUser = this.userService.findByUsername(customer.email);

        if (existUser.isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        Customer newCustomer = this.customerService.Create(customer);

        /*String verificationLink = "http://localhost:8080/api/verify/" + newCustomer.getId();
        String verificationMail = generateVerificationEmail(user.getUsername(), verificationLink);

        emailService.sendEmail(user.getEmail(), "Verification email", verificationMail);
        System.out.println("Email poslat valjda...");
*/
        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }

    private String generateVerificationEmail(String userName, String verificationLink) {
        return String.format("Subject: Verify Your Profile\n\n" +
                "Dear %s,\n\n" +
                "Thank you for signing up with our medical equipment system!\n\n" +
                "To complete the registration process, please click the following link to verify your email address:\n\n" +
                "%s\n\n" +
                "Best regards,\n" +
                "Marko, Uros and Filip", userName, verificationLink);
    }

    @PostMapping(value = "login", consumes = "application/json")
    public ResponseEntity<UserTokenState> login(@RequestBody JwtAuthenticationRequest loginDto, HttpServletRequest request){
        return ResponseEntity.ok(userService.login(loginDto));
    }
}
