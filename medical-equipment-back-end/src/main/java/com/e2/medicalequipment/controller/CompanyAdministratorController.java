package com.e2.medicalequipment.controller;

import com.e2.medicalequipment.dto.CompanyAdministratorDTO;
import com.e2.medicalequipment.dto.EquipmentTrackingDTO;
import com.e2.medicalequipment.dto.UpdateCompanyAdministratorDTO;
import com.e2.medicalequipment.dto.UpdateCompanyDTO;
import com.e2.medicalequipment.model.Company;
import com.e2.medicalequipment.model.CompanyAdministrator;
import com.e2.medicalequipment.model.Customer;
import com.e2.medicalequipment.service.CompanyAdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('SYSTEM_ADMINISTRATOR')")
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
    @PreAuthorize("hasAuthority('COMPANY_ADMINISTRATOR')")
    public ResponseEntity<CompanyAdministrator> getCompanyAdministrator(@PathVariable String id) throws Exception {
        CompanyAdministrator companyAdministrator = null;
        try {
            companyAdministrator = companyAdministratorService.Get(id);
            return new ResponseEntity<CompanyAdministrator>(companyAdministrator, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<CompanyAdministrator>(companyAdministrator, HttpStatus.CONFLICT);
        }    }

    @PutMapping(value = "/profile/edit", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<CompanyAdministrator> updateCompanyAdministrator(@RequestBody UpdateCompanyAdministratorDTO companyAdministratorDTO) {
        CompanyAdministrator companyAdministrator = null;
        try {
            companyAdministrator = companyAdministratorService.Update(companyAdministratorDTO);
            return new ResponseEntity<CompanyAdministrator>(companyAdministrator, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<CompanyAdministrator>(companyAdministrator, HttpStatus.CONFLICT);

        }
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<CompanyAdministrator>> getCompanyAdministrators() throws Exception {
        List<CompanyAdministrator> administrators = companyAdministratorService.GetAll();
        return new ResponseEntity<List<CompanyAdministrator>>(administrators, HttpStatus.OK);
    }
}
