package com.e2.medicalequipment.service;

import com.e2.medicalequipment.dto.CreateCompanyDTO;
import com.e2.medicalequipment.dto.EquipmentTrackingDTO;
import com.e2.medicalequipment.dto.UpdateCompanyDTO;
import com.e2.medicalequipment.model.*;
import com.e2.medicalequipment.repository.CompanyRepository;
import com.e2.medicalequipment.repository.EquipmentTrackingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EquipmentTrackingServiceImpl implements EquipmentTrackingService {

    @Autowired
    private EquipmentTrackingRepository equipmentTrackingRepository;

    @Override
    public List<EquipmentTrackingDTO> GetAll() throws Exception {
        List<EquipmentTrackingDTO> result = new ArrayList<>();
        for(EquipmentTracking e : equipmentTrackingRepository.findAll()){
            result.add( new EquipmentTrackingDTO(e));
        }
        return result;
    }

    @Override
    public List<EquipmentTrackingDTO> GetWanted(String searchName,String type) throws Exception {
        List<EquipmentTrackingDTO> allEquipment = new ArrayList<>();
        for(EquipmentTracking e : equipmentTrackingRepository.findAll()) {
                allEquipment.add(new EquipmentTrackingDTO(e));
        }
        return allEquipment.stream()
                    .filter(e -> searchName.equals("empty") || e.equipment.name.toLowerCase().contains(searchName.toLowerCase()))
                    .filter(e -> type.equals("empty") || e.equipment.type.toString().equals(type))
                    .collect(Collectors.toList());
    }
}
