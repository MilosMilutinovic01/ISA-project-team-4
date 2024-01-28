package com.e2.medicalequipment.service;

import com.e2.medicalequipment.dto.LatLngDTO;

import java.util.List;

public interface SimulatorService {
    public void StartSimulator(List<LatLngDTO> coordinates, String forUser, String refreshRate);
}
