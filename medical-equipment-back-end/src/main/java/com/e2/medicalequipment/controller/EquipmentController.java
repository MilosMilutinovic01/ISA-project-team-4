package com.e2.medicalequipment.controller;

import com.e2.medicalequipment.dto.CreateCompanyDTO;
import com.e2.medicalequipment.model.Company;
import com.e2.medicalequipment.model.Equipment;
import com.e2.medicalequipment.service.EquipmentService;
import com.e2.medicalequipment.dto.EquipmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/equipment", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<EquipmentDTO>> getAll(){
        List<EquipmentDTO> equipment = null;
        try {
            equipment = equipmentService.GetAll();
            return new ResponseEntity<List<EquipmentDTO>>(equipment, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<EquipmentDTO>>(equipment, HttpStatus.CONFLICT);
        }
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('COMPANY_ADMINISTRATOR')")
    public ResponseEntity<Equipment> registerEquipment(@RequestBody EquipmentDTO equipmentDto) {
        Equipment savedEquipment = null;
        try {
            System.out.println("Thread id: " + Thread.currentThread().getId());
            savedEquipment = equipmentService.Create(equipmentDto);
            return new ResponseEntity<Equipment>(savedEquipment, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Equipment>(savedEquipment, HttpStatus.CONFLICT);
        }
    }
}

