package com.e2.medicalequipment.service;

import com.e2.medicalequipment.dto.EquipmentDTO;
import com.e2.medicalequipment.dto.EquipmentTrackingDTO;
import com.e2.medicalequipment.model.*;
import com.e2.medicalequipment.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
}

