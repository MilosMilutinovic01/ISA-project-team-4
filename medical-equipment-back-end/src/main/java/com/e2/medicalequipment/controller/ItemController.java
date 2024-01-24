package com.e2.medicalequipment.controller;

import com.e2.medicalequipment.dto.CreateAppointmentDTO;
import com.e2.medicalequipment.dto.CreateCompanyDTO;
import com.e2.medicalequipment.dto.CreateItemDTO;
import com.e2.medicalequipment.dto.UpdateItemDTO;
import com.e2.medicalequipment.model.Appointment;
import com.e2.medicalequipment.model.Company;
import com.e2.medicalequipment.model.Customer;
import com.e2.medicalequipment.model.Item;
import com.e2.medicalequipment.service.EquipmentService;
import com.e2.medicalequipment.service.ItemService;
import com.e2.medicalequipment.service.QRCodeService;
import com.e2.medicalequipment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/items", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class ItemController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private QRCodeService qrCodeService;

    @Autowired
    private UserService userService;

    @Autowired
    private EquipmentService equipmentService;

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

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity<List<Item>> getAllByCustomerId(@PathVariable String id){
        List<Item> items = null;
        try {
            items = itemService.GetAllByCustomerId(id);
            return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<Item>>(items, HttpStatus.CONFLICT);
        }
    }

    @GetMapping(value = "byAppointment/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @PreAuthorize("hasAuthority('COMPANY_ADMINISTRATOR')")
    public ResponseEntity<Customer>  getCustomerByAppointmentId(@PathVariable String id) {
        Customer customer = null;
        try {
            customer = itemService.GetCustomerByAppointmentId(id);
            return new ResponseEntity<Customer>(customer, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Customer>(customer, HttpStatus.CONFLICT);
        }
    }

        @PostMapping(value = "/reserve", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        @PreAuthorize("hasAuthority('CUSTOMER')")
        public boolean reserve (@RequestBody List < UpdateItemDTO > items) throws Exception {
            long userId = 0;
            int price = 0;
            long appointmentId = 0;
            String message = "Broj rezervacije:" + items.get(0).AppointmentId + "\nRezervisana oprema:\n";
            for (UpdateItemDTO item : items) {
                userId = item.CustomerId;
                price += equipmentService.Get(item.EquipmentId).getPrice() * item.Count;
                message += equipmentService.Get(item.EquipmentId).getName()+"\n";
                itemService.Update(item);
            }
            message += "Ukupna cena: " + price;
            qrCodeService.sendQRCode("Your cart", userService.getUserById(userId).getUsername(), message);
            return true;
        }
    }
