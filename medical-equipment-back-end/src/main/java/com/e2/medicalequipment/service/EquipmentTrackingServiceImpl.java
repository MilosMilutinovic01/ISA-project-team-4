package com.e2.medicalequipment.service;

import com.e2.medicalequipment.dto.CreateCompanyDTO;
import com.e2.medicalequipment.dto.EquipmentTrackingDTO;
import com.e2.medicalequipment.dto.UpdateCompanyDTO;
import com.e2.medicalequipment.model.*;
import com.e2.medicalequipment.repository.CompanyRepository;
import com.e2.medicalequipment.repository.EquipmentRepository;
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
    public EquipmentTracking Get(String id) {
        return equipmentTrackingRepository.findById(Long.valueOf(id)).get();
    }
    public EquipmentTracking Update(EquipmentTrackingDTO dto) throws Exception{
        EquipmentTracking equipmentTracking = new EquipmentTracking(dto);
        Equipment equipment = new Equipment(dto.equipment);
        Company company = new Company(dto.company);
        if ((equipmentTracking.getId() == null) || (equipment.getId() == null) || (company.getId() == null)){
            throw new Exception("ID must not be null for updating entity.");
        }
        equipmentTracking.setCompany(company);
        equipmentTracking.setEquipment(equipment);
        equipmentTracking.setCount(dto.count);
        EquipmentTracking savedEquipmentTracking = equipmentTrackingRepository.save(equipmentTracking);
        return savedEquipmentTracking;
    }
    @Override
    public List<EquipmentTrackingDTO> GetByEquipmentId(String equipmentId) throws Exception {
        List<EquipmentTrackingDTO> result = new ArrayList<>();
        for(EquipmentTracking e : equipmentTrackingRepository.findAllByEquipmentId(equipmentId)){
            result.add( new EquipmentTrackingDTO(e));
        }
        return result;
    }
}
