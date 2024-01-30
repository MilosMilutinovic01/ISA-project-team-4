package com.e2.medicalequipment.controller;

import com.e2.medicalequipment.dto.*;
import com.e2.medicalequipment.model.Company;
import com.e2.medicalequipment.model.Contract;
import com.e2.medicalequipment.model.Customer;
import com.e2.medicalequipment.model.Item;
import com.e2.medicalequipment.service.CompanyAdministratorService;
import com.e2.medicalequipment.service.CompanyService;
import com.e2.medicalequipment.service.ContractService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.e2.medicalequipment.kafka.ContractProducer;

import java.util.List;

@RestController
@RequestMapping(value = "/api/contracts", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin

public class ContractController {
    @Autowired
    private ContractService contractService;

    @Autowired
    public ContractProducer contractProducer;

    @PostMapping(value = "/new", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Contract> newContract(@RequestBody ContractDTO contractDto) {
        Contract savedContract = null;
        try {
            savedContract = contractService.Create(contractDto);
            return new ResponseEntity<Contract>(savedContract, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Contract>(savedContract, HttpStatus.CONFLICT);
        }
    }
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('SYSTEM_ADMINISTRATOR')")
    @ResponseBody
    public ResponseEntity<List<Contract>> getAll(){
        List<Contract> contracts = null;
        try {
            contracts = contractService.GetAll();
            return new ResponseEntity<List<Contract>>(contracts, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<Contract>>(contracts, HttpStatus.CONFLICT);
        }
    }

    @PostMapping(value = "/update/{hospital}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('SYSTEM_ADMINISTRATOR')")
    public ResponseEntity<Contract>  updateCancellation (@RequestBody boolean cancel,@PathVariable String hospital) throws Exception {
        Contract savedContract = null;
        try {
            savedContract = contractService.UpdateCancellation(hospital,cancel);
            return new ResponseEntity<Contract>(savedContract, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Contract>(savedContract, HttpStatus.CONFLICT);
        }
    }

    @PostMapping(value = "/send")
    public String sendMessage(@RequestParam("message") String message) {
        contractProducer.sendMessage(message);
        return "Message sent: " + message;
    }
}

