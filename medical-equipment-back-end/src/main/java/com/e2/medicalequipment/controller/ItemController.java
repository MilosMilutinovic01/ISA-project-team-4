package com.e2.medicalequipment.controller;

import com.e2.medicalequipment.dto.*;
import com.e2.medicalequipment.model.Appointment;
import com.e2.medicalequipment.model.Company;
import com.e2.medicalequipment.model.Customer;
import com.e2.medicalequipment.model.Item;
import com.e2.medicalequipment.service.*;
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

    @Autowired
    private CompanyService companyService;

    @Autowired
    private EquipmentTrackingService equipmentTrackingService;

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
            String message = "Broj rezervacije : " + items.get(0).AppointmentId + "\n\nRezervisana oprema :\n";
            for (UpdateItemDTO item : items) {
                try {
                    EquipmentTrackingDTO dto = new EquipmentTrackingDTO(equipmentTrackingService.FindByCompanyAndEquipment(item.CompanyId,item.EquipmentId));
                    dto.count -= item.Count;
                    equipmentTrackingService.Update(dto);
                    userId = item.CustomerId;
                    price += equipmentService.Get(item.EquipmentId).getPrice() * item.Count;

                    UpdateItemDTO updatedItem = new UpdateItemDTO();
                    updatedItem.CompanyId = item.CompanyId;
                    updatedItem.EquipmentId = item.EquipmentId;
                    updatedItem.Count = item.Count;
                    updatedItem.CustomerId = item.CustomerId;
                    updatedItem.AppointmentId = item.AppointmentId;
                    updatedItem.Id = item.Id;
                    message += equipmentService.Get(item.EquipmentId).getName()+ " - " + item.Count + " kom\n";
                    itemService.Update(updatedItem);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw e;  // rethrow the exception
                }
            }
            message += "\nUkupna cena : " + price + " RSD\n";
            //
            //dto.id =
            //equipmentTrackingService.Update()
            qrCodeService.sendQRCode("Your cart", userService.getUserById(userId).getUsername(), message);
            return true;
        }
    }
