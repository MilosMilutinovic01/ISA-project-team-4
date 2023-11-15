package com.e2.medicalequipment.controller;

import com.e2.medicalequipment.model.Customer;
import com.e2.medicalequipment.service.CustomerService;
import com.e2.medicalequipment.dto.CreateCustomerDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @GetMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

    @Operation(summary = "Create new test", description = "Create new test", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class)) }),
            @ApiResponse(responseCode = "409", description = "Not possible to create new test when given id is not null",
                    content = @Content)
    })
    @PostMapping(value = "/register",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> createTest(@RequestBody CreateCustomerDTO customerDto)  {
        Customer savedCustomer = null;
        try {
            savedCustomer = customerService.Create(customerDto);
            return new ResponseEntity<Customer>(savedCustomer, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Customer>(savedCustomer, HttpStatus.CONFLICT);
        }
    }
}
