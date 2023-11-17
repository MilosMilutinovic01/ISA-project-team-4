package com.e2.medicalequipment.controller;

import com.e2.medicalequipment.model.Company;
import com.e2.medicalequipment.service.CompanyService;
import com.e2.medicalequipment.dto.CreateCompanyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/companies", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    @PostMapping(value = "/register",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Company> registerCompany(@RequestBody CreateCompanyDTO companyDto)  {
        Company savedCompany = null;
        try {System.out.println("Thread id: " + Thread.currentThread().getId());
            savedCompany = companyService.Create(companyDto);
            return new ResponseEntity<Company>(savedCompany, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Company>(savedCompany, HttpStatus.CONFLICT);
        }
    }
}
