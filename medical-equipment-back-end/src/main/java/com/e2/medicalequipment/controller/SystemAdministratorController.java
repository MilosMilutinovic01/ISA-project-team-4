package com.e2.medicalequipment.controller;

import com.e2.medicalequipment.dto.SystemAdministratorDTO;
import com.e2.medicalequipment.dto.UpdateCustomerDTO;
import com.e2.medicalequipment.model.Customer;
import com.e2.medicalequipment.model.SystemAdministrator;
import com.e2.medicalequipment.service.SystemAdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/systemAdministrators", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class SystemAdministratorController {
    @Autowired
    private SystemAdministratorService systemAdministratorService;

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('SYSTEM_ADMINISTRATOR')")
    public ResponseEntity<SystemAdministrator> registerSystemAdministrator(@RequestBody SystemAdministratorDTO systemAdministratorDto)  {
        SystemAdministrator savedSystemAdministrator = null;
        try {System.out.println("Thread id: " + Thread.currentThread().getId());
            savedSystemAdministrator = systemAdministratorService.Create(systemAdministratorDto);
            return new ResponseEntity<SystemAdministrator>(savedSystemAdministrator, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<SystemAdministrator>(savedSystemAdministrator, HttpStatus.CONFLICT);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('SYSTEM_ADMINISTRATOR')")
    @ResponseBody
    public ResponseEntity<SystemAdministrator> getSystemAdministrator(@PathVariable Long id){
        SystemAdministrator systemAdministrator = null;
        System.out.println("usao");
        try {
            systemAdministrator = systemAdministratorService.Get(id);
            return new ResponseEntity<SystemAdministrator>(systemAdministrator, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<SystemAdministrator>(systemAdministrator, HttpStatus.CONFLICT);
        }
    }

    @PutMapping(value = "/changePassword", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('SYSTEM_ADMINISTRATOR')")
    @ResponseBody
    public ResponseEntity<SystemAdministrator> updateSystemAdministrator(@RequestBody SystemAdministratorDTO systemAdministratorDTO){
        SystemAdministrator systemAdministrator = null;
        try {
            systemAdministrator = systemAdministratorService.Update(systemAdministratorDTO);
            return new ResponseEntity<SystemAdministrator>(systemAdministrator, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<SystemAdministrator>(systemAdministrator, HttpStatus.CONFLICT);
        }
    }
}

