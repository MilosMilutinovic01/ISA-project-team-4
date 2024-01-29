package com.e2.medicalequipment.controller;

import com.e2.medicalequipment.dto.LatLngDTO;
import com.e2.medicalequipment.service.SimulatorService;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/simulator", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class SimulatorController {
    @Autowired
    SimulatorService simulatorService;

    @PostMapping(value = "/start")
    @PreAuthorize("hasAuthority('COMPANY_ADMINISTRATOR')")
    public Response StartSimulator(@RequestBody List<LatLngDTO> coordinates, @RequestParam("user") String forUser, @RequestParam("refreshRate") String refreshRate)
    {
        simulatorService.StartSimulator(coordinates,forUser,refreshRate);
        return Response.ok().build();
    }
}
