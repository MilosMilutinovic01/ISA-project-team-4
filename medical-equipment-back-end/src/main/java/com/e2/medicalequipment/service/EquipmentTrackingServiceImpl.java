package com.e2.medicalequipment.service;

import com.e2.medicalequipment.dto.CreateCompanyDTO;
import com.e2.medicalequipment.dto.UpdateCompanyDTO;
import com.e2.medicalequipment.model.*;
import com.e2.medicalequipment.repository.CompanyRepository;
import com.e2.medicalequipment.repository.EquipmentTrackingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

@Service
public class EquipmentTrackingServiceImpl implements EquipmentTrackingService {

    @Autowired
    private EquipmentTrackingRepository equipmentTrackingRepository;

    @Override
    public List<EquipmentTracking> GetAll() throws Exception {
        return equipmentTrackingRepository.findAll();
    }
}
