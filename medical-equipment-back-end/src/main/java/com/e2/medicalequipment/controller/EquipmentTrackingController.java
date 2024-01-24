package com.e2.medicalequipment.controller;

import com.e2.medicalequipment.dto.EquipmentDTO;
import com.e2.medicalequipment.dto.EquipmentTrackingDTO;
import com.e2.medicalequipment.dto.UpdateCompanyDTO;
import com.e2.medicalequipment.model.Company;
import com.e2.medicalequipment.model.EquipmentTracking;
import com.e2.medicalequipment.service.EquipmentTrackingService;
import com.e2.medicalequipment.dto.CreateCompanyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @GetMapping(value = "/equipment/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<EquipmentTrackingDTO>> getByEquipment(@PathVariable String id) throws Exception {

        List<EquipmentTrackingDTO> trackings = equipmentTrackingService.GetByEquipmentId(id);
        return new ResponseEntity<List<EquipmentTrackingDTO>>(trackings, HttpStatus.OK);
    }
    @PutMapping(value = "/edit", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @PreAuthorize("hasAuthority('COMPANY_ADMINISTRATOR')")
    public ResponseEntity<EquipmentTracking> updateEquipmentTracking(@RequestBody EquipmentTrackingDTO equipmentTrackingDTO) {
        EquipmentTracking equipmentTracking = null;
        try {
            equipmentTracking = equipmentTrackingService.Update(equipmentTrackingDTO);
            return new ResponseEntity<EquipmentTracking>(equipmentTracking, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<EquipmentTracking>(equipmentTracking, HttpStatus.CONFLICT);

        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<EquipmentTrackingDTO> getEquipmentTracking (@PathVariable String id) throws Exception {
        EquipmentTracking equipmentTracking = equipmentTrackingService.Get(id);
        if (equipmentTracking == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new EquipmentTrackingDTO(equipmentTracking), HttpStatus.OK);
    }
}
