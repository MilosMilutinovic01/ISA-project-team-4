package com.e2.medicalequipment.controller;

import com.e2.medicalequipment.dto.CreateAppointmentDTO;
import com.e2.medicalequipment.model.Appointment;
import com.e2.medicalequipment.model.Item;
import com.e2.medicalequipment.service.AppointmentService;
import com.e2.medicalequipment.service.QRCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/appointments", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private QRCodeService qrCodeService;
   @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('COMPANY_ADMINISTRATOR')")
    public ResponseEntity<Appointment> registerAppointment(@RequestBody CreateAppointmentDTO appointmentDTO) {
        System.out.println("\n\nCreate appointment");
        Appointment savedAppointment = null;
        try {
            System.out.println("Thread id: " + Thread.currentThread().getId());
            savedAppointment = appointmentService.Create(appointmentDTO);
            return new ResponseEntity<Appointment>(savedAppointment, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Appointment>(savedAppointment, HttpStatus.CONFLICT);
        }
    }

    @PostMapping(value = "/registerIrregular", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity<Appointment> registerIrregularAppointment(@RequestBody CreateAppointmentDTO appointmentDTO) {
        Appointment savedAppointment = null;
        try {
            System.out.println("Thread id: " + Thread.currentThread().getId());
            savedAppointment = appointmentService.CreateIrregular(appointmentDTO);
            savedAppointment.setIsPredefined(false);
            return new ResponseEntity<Appointment>(savedAppointment, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Appointment>(savedAppointment, HttpStatus.CONFLICT);
        }
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Appointment>> getAll(){
        List<Appointment> appointments = null;
        try {
            appointments = appointmentService.GetAll();
            return new ResponseEntity<List<Appointment>>(appointments, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<Appointment>>(appointments, HttpStatus.CONFLICT);
        }
    }

    @GetMapping(value = "/free/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Appointment>> getFreeAppointmentsByCompanyId(@PathVariable String id){
        List<Appointment> appointments = null;
        try {
            appointments = appointmentService.GetFreeByCompanyId(Long.parseLong(id));
            return new ResponseEntity<List<Appointment>>(appointments, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<Appointment>>(appointments, HttpStatus.CONFLICT);
        }
    }

    @GetMapping(value = "/scheduled/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Appointment>> getScheduledAppointmentsByCompanyId(@PathVariable String id){
        List<Appointment> appointments = null;
        try {
            appointments = appointmentService.GetScheduledByCompanyId(Long.parseLong(id));
            return new ResponseEntity<List<Appointment>>(appointments, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<Appointment>>(appointments, HttpStatus.CONFLICT);
        }
    }

    @GetMapping(value = "/scheduledCustomer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Appointment>> getScheduledAppointmentsByCustomerId(@PathVariable String id){
        List<Appointment> appointments = null;
        try {
            appointments = appointmentService.GetScheduledByCustomerId(Long.parseLong(id));
            return new ResponseEntity<List<Appointment>>(appointments, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<Appointment>>(appointments, HttpStatus.CONFLICT);
        }
    }

  /*  @GetMapping(value = "/available/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity<List<Appointment>> getAvailableByCompanyId(@PathVariable String id){
        List<Appointment> appointments = null;
        try {
            appointments = appointmentService.GetAvailableByCompanyId(Long.parseLong(id));
            return new ResponseEntity<List<Appointment>>(appointments, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<Appointment>>(appointments, HttpStatus.CONFLICT);
        }
    }

    @GetMapping(value = "/reserved/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity<List<Appointment>> getReservedByCompanyId(@PathVariable String id){
        List<Appointment> appointments = null;
        try {
            appointments = appointmentService.GetReservedByCompanyId(Long.parseLong(id));
            return new ResponseEntity<List<Appointment>>(appointments, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<Appointment>>(appointments, HttpStatus.CONFLICT);
        }
    }
    */
    @GetMapping(value = "/dataFromQR/{fileName}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> getAppointmentDataFromQRCode(@PathVariable String fileName){
        String result = null;
        try {
            result = qrCodeService.readQRCode(fileName);
            return new ResponseEntity<String>(result,HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(result,HttpStatus.CONFLICT);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Appointment> getById(@PathVariable String id){
        Appointment appointment = null;
        try {
            appointment = appointmentService.GetById(Long.parseLong(id));
            return new ResponseEntity<Appointment>(appointment, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Appointment>(appointment, HttpStatus.CONFLICT);
        }
    }
    @GetMapping(value = "/checkReservation/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Boolean> checkReservation(@PathVariable String id){
        Boolean result = false;
        try {
            result = appointmentService.CheckReservation(Long.parseLong(id));
            return new ResponseEntity<Boolean>(result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Boolean>(result, HttpStatus.CONFLICT);
        }
    }
}
