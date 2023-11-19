package com.e2.medicalequipment.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.e2.medicalequipment.dto.UpdateCustomerDTO;
import com.e2.medicalequipment.model.Customer;
import com.e2.medicalequipment.service.CustomerService;
import com.e2.medicalequipment.dto.CreateCustomerDTO;
import com.e2.medicalequipment.service.EmailService;
import jakarta.ws.rs.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private EmailService emailService;

    @GetMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

    @GetMapping(value = "/profile/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Customer> getCustomer(@PathVariable String id){
        System.out.println("ID: "+ id);
        Customer customer = null;
        try {
            customer = customerService.Get(id);
            return new ResponseEntity<Customer>(customer, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Customer>(customer, HttpStatus.CONFLICT);
        }
    }

    @PutMapping(value = "/profile/edit", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Customer> updateCustomer(@RequestBody UpdateCustomerDTO customerDTO){
        Customer customer = null;
        try {
            customer = customerService.Update(customerDTO);
            return new ResponseEntity<Customer>(customer, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Customer>(customer, HttpStatus.CONFLICT);
        }
    }

    @PostMapping(value = "/register",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> createTest(@RequestBody CreateCustomerDTO customerDto)  {
        Customer savedCustomer = null;
        try {System.out.println("Thread id: " + Thread.currentThread().getId());
            emailService.sendNotificaitionAsync(customerDto);
            savedCustomer = customerService.Create(customerDto);
            return new ResponseEntity<Customer>(savedCustomer, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Customer>(savedCustomer, HttpStatus.CONFLICT);
        }
    }


}
