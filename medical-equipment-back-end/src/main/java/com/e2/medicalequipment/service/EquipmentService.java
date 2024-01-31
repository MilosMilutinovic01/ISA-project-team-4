package com.e2.medicalequipment.service;

import com.e2.medicalequipment.dto.EquipmentDTO;
import com.e2.medicalequipment.model.Equipment;
import com.e2.medicalequipment.model.User;

import java.util.ArrayList;
import java.util.List;

public interface EquipmentService {
    List<EquipmentDTO> GetAll() throws Exception;
    Equipment Create(EquipmentDTO equipmentDTO) throws Exception;
    public Equipment Get(Long id);
    public List<EquipmentDTO> GetWanted(String searchName,String type) throws Exception;
}



