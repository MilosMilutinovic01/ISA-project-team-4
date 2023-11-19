package com.e2.medicalequipment.controller;

import com.e2.medicalequipment.dto.EquipmentTrackingDTO;
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
    public ResponseEntity<List<EquipmentTrackingDTO>> getEquipmentTrackings() throws Exception {
        List<EquipmentTrackingDTO> trackings = equipmentTrackingService.GetAll();
        return new ResponseEntity<List<EquipmentTrackingDTO>>(trackings, HttpStatus.OK);
    }
    @GetMapping(value = "/search/{name}/{type}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<EquipmentTrackingDTO>> filterAndSearch(@PathVariable String name,
                                                                      @PathVariable String type
                                                                      ) throws Exception {

        List<EquipmentTrackingDTO> trackings = equipmentTrackingService.GetWanted(name,type);
        return new ResponseEntity<List<EquipmentTrackingDTO>>(trackings, HttpStatus.OK);
    }
}
