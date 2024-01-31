package com.e2.medicalequipment.controller;

import com.e2.medicalequipment.dto.*;
import com.e2.medicalequipment.model.*;
import com.e2.medicalequipment.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    private CustomerService customerService;

    @Autowired
    private EquipmentTrackingService equipmentTrackingService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private EmailService emailService;

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


    @GetMapping(value = "/appointment/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity<List<Item>> getAllByAppointmentId(@PathVariable String id){
        List<Item> items = null;
        try {
            items = itemService.GetAllByAppointmentId(id);
            return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<Item>>(items, HttpStatus.CONFLICT);
        }
    }

    @GetMapping(value = "customerByAppointment/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
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
                    userId = item.CustomerId;
                    price += equipmentService.Get(item.EquipmentId).getPrice() * item.Count;

                    UpdateItemDTO updatedItem = new UpdateItemDTO();
                    updatedItem.CompanyId = item.CompanyId;
                    updatedItem.EquipmentId = item.EquipmentId;
                    updatedItem.Count = item.Count;
                    updatedItem.CustomerId = item.CustomerId;
                    updatedItem.AppointmentId = item.AppointmentId;
                    updatedItem.Id = item.Id;
                    updatedItem.PickedUp = item.PickedUp;
                    message += equipmentService.Get(item.EquipmentId).getName()+ " - " + item.Count + " kom\n";
                    itemService.Update(updatedItem);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw e;
                }
            }

            message += "\nUkupna cena : " + price + " RSD\n";

            qrCodeService.sendQRCode("Your cart", userService.getUserById(userId).getUsername(), message, items.get(0).AppointmentId);
            return true;
        }


    @PostMapping(value = "/cancelReservation", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public boolean cancel(@RequestBody Map<String, Object> requestBody) throws Exception {
        String id = (String) requestBody.get("id");
        Integer penalty = (Integer) requestBody.get("penalty");
        Appointment a = appointmentService.FindById(Long.parseLong(id));
        Long userId = 0L;
        if(a.getIsPredefined() == false)
            appointmentService.Delete(a.getId());
        List<Item> items = new ArrayList<>();
        items = itemService.GetAllByAppointmentId(id);
        for(Item i: items){
            userId = i.getCustomer().getId();
            /*EquipmentTracking et = equipmentTrackingService.FindByCompanyAndEquipment(i.getCompany().getId(),i.getEquipment().getId());
            EquipmentTrackingDTO etdto = new EquipmentTrackingDTO(et);
            etdto.count += i.getCount();
            equipmentTrackingService.Update(etdto);*/
            itemService.Delete(i.getId());
        }
        userService.changePenaltyPoints(penalty,userId);
        return true;
    }

    @PostMapping(value = "/pickUp", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('COMPANY_ADMINISTRATOR')")
    public boolean pickUp (@RequestBody String appointmentId) throws Exception {
        long userId = 0;
        List<Item> items = itemService.GetAllByAppointmentId(appointmentId);
        String message = "Broj rezervacije : " + appointmentId + "\n\nRezervisana oprema :\n";
        for (Item item : items) {
            try {
                EquipmentTrackingDTO dto = new EquipmentTrackingDTO(equipmentTrackingService.FindByCompanyAndEquipment(item.getCompany().getId(),item.getEquipment().getId()));
                dto.count -= item.getCount();
                equipmentTrackingService.Update(dto);
                userId = item.getCustomer().getId();
                itemService.Process(item,true);
                message += equipmentService.Get(item.getEquipment().getId()).getName()+ " - " + item.getCount() + " kom\n";
            } catch (Exception e) {
                e.printStackTrace();
                throw e;  // rethrow the exception
            }
        }
        message += "\n\nUspesno preuzeta oprema!";
        emailService.sendNotificaitionAsync(userService.getUserById(userId).getUsername(), "Potvrda o uspesno preuzetoj opremi", "<html>" + message.replace("\n", "<br/>") + "</html>");
        return true;
    }
    @PostMapping(value = "/processExpired", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('COMPANY_ADMINISTRATOR')")
    public boolean processExpired (@RequestBody String appointmentId) throws Exception {
        long userId = 0;
        List<Item> items = itemService.GetAllByAppointmentId(appointmentId);
        for (Item item : items) {
            try {
                userId = item.getCustomer().getId();
                itemService.Process(item,false);
            } catch (Exception e) {
                e.printStackTrace();
                throw e;  // rethrow the exception
            }
        }
        customerService.GivePenaltyPoints(userId);
        return true;
    }
}
