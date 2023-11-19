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

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Company>> getAll(){
        List<Company> companies = null;
        try {
            companies = companyService.GetAll();
            return new ResponseEntity<List<Company>>(companies, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<Company>>(companies, HttpStatus.CONFLICT);
        }
    }

    @GetMapping(value = "/search/{name}/{street}/{city}/{country}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Company>> searchCompanies(@PathVariable String name,
                                                         @PathVariable String street,
                                                         @PathVariable String city,
                                                         @PathVariable String country){
        List<Company> searchedCompanies = null;
        try {
            searchedCompanies = companyService.Search(name, street, city, country);
            return new ResponseEntity<List<Company>>(searchedCompanies, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<Company>>(searchedCompanies, HttpStatus.CONFLICT);
        }
    }

    @PutMapping(value = "/filter/{rate}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Company>> filterCompanies(@PathVariable String rate,
                                                         @RequestBody List<Company> companies){
        List<Company> filteredCompanies = null;
        try {
            filteredCompanies = companyService.Filter(rate, companies);
            return new ResponseEntity<List<Company>>(filteredCompanies, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<Company>>(filteredCompanies, HttpStatus.CONFLICT);
        }
    }
}
