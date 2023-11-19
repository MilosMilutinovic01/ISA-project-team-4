package com.e2.medicalequipment.controller;

import com.e2.medicalequipment.dto.CompanyAdministratorDTO;
import com.e2.medicalequipment.dto.UpdateCompanyDTO;
import com.e2.medicalequipment.model.Company;
import com.e2.medicalequipment.model.CompanyAdministrator;
import com.e2.medicalequipment.service.CompanyAdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/companyAdministrators", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class CompanyAdministratorController {
    @Autowired
    private CompanyAdministratorService companyAdministratorService;

    @GetMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompanyAdministrator> registerCompanyAdministrator(@RequestBody CompanyAdministratorDTO companyAdministratorDto)  {
        CompanyAdministrator savedCompanyAdministrator = null;
        try {System.out.println("Thread id: " + Thread.currentThread().getId());
            savedCompanyAdministrator = companyAdministratorService.Create(companyAdministratorDto);
            return new ResponseEntity<CompanyAdministrator>(savedCompanyAdministrator, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<CompanyAdministrator>(savedCompanyAdministrator, HttpStatus.CONFLICT);
        }
    }

    @GetMapping(value = "/profile/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<CompanyAdministratorDTO> getCompanyAdministrator(@PathVariable String id) throws Exception {
        CompanyAdministrator companyAdministrator = companyAdministratorService.Get(id);
        if (companyAdministrator == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new CompanyAdministratorDTO(companyAdministrator), HttpStatus.OK);
    }
/*
    @PutMapping(value = "/profile/edit", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<CompanyAdministrator> updateCompanyAdministrator(@RequestBody CompanyAdministratorDTO companyAdministratorDTO) {
        CompanyAdministrator companyAdministrator = null;
        try {
            companyAdministrator = companyAdministratorService.Update(companyAdministratorDTO);
            return new ResponseEntity<CompanyAdministrator>(companyAdministrator, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<CompanyAdministrator>(companyAdministrator, HttpStatus.CONFLICT);

        }
    }

 */
}
