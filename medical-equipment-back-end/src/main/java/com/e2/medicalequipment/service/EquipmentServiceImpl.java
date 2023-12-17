package com.e2.medicalequipment.service;

import com.e2.medicalequipment.dto.CreateCompanyDTO;
import com.e2.medicalequipment.dto.EquipmentDTO;
import com.e2.medicalequipment.dto.EquipmentTrackingDTO;
import com.e2.medicalequipment.model.*;
import com.e2.medicalequipment.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;
    @Override
    public List<EquipmentDTO> GetAll() throws Exception {
        List<EquipmentDTO> allEquipment = new ArrayList<>();
        for(Equipment e : equipmentRepository.findAll()) {
            allEquipment.add(new EquipmentDTO(e));
        }
        return allEquipment;
    }

    @Override
    public Equipment Create(EquipmentDTO equipmentDTO) throws Exception {
        Equipment equipment = new Equipment(equipmentDTO);
        equipment.setDescription(equipmentDTO.description);
        equipment.setName(equipmentDTO.name);
        equipment.setType(equipmentDTO.type);
        equipment.setPrice(equipmentDTO.price);

        if (equipment.getId() != null) {
            throw new Exception("ID must be null for a new entity.");
        }
        Equipment savedEquipment = equipmentRepository.save(equipment);
        return savedEquipment;
    }
}

