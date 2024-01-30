package com.e2.medicalequipment.service;

import com.e2.medicalequipment.dto.ContractDTO;
import com.e2.medicalequipment.dto.CreateItemDTO;
import com.e2.medicalequipment.dto.EquipmentTrackingDTO;
import com.e2.medicalequipment.dto.UpdateItemDTO;
import com.e2.medicalequipment.model.Contract;
import com.e2.medicalequipment.model.Item;

import java.util.List;

public interface ContractService {

    Contract Create(ContractDTO contractDTO) throws Exception;
    Contract Update(ContractDTO contractDTO) throws Exception;
    List<ContractDTO> GetAll() throws Exception;

}
