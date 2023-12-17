package com.e2.medicalequipment.controller;

import com.e2.medicalequipment.dto.CreateAppointmentDTO;
import com.e2.medicalequipment.model.Appointment;
import com.e2.medicalequipment.service.AppointmentService;
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

   @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('COMPANY_ADMINISTRATOR')")
    public ResponseEntity<Appointment> registerAppointment(@RequestBody CreateAppointmentDTO appointmentDTO) {
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
}
