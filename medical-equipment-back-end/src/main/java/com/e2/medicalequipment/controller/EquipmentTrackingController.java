package com.e2.medicalequipment.controller;

import com.e2.medicalequipment.model.EquipmentTracking;
import com.e2.medicalequipment.service.EquipmentTrackingService;
import com.e2.medicalequipment.dto.CreateCompanyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/equipmentTracking", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class EquipmentTrackingController {
    @Autowired
    private EquipmentTrackingService equipmentTrackingService;
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<EquipmentTracking>> getEquipmentTrackings() throws Exception {
        List<EquipmentTracking> trackings = equipmentTrackingService.GetAll();
        return new ResponseEntity<List<EquipmentTracking>>(trackings, HttpStatus.OK);
    }
}
