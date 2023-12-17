package com.e2.medicalequipment.controller;

import com.e2.medicalequipment.dto.CreateCompanyDTO;
import com.e2.medicalequipment.dto.CreateItemDTO;
import com.e2.medicalequipment.model.Company;
import com.e2.medicalequipment.model.Item;
import com.e2.medicalequipment.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/items", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity<Item> createItem(@RequestBody CreateItemDTO itemDto) {
        Item savedItem = null;
        try {
            savedItem = itemService.Create(itemDto);
            return new ResponseEntity<Item>(savedItem, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Item>(savedItem, HttpStatus.CONFLICT);
        }
    }
}
