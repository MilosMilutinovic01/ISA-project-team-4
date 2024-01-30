package com.e2.medicalequipment.service;

import com.e2.medicalequipment.dto.ContractDTO;
import com.e2.medicalequipment.dto.EquipmentDTO;
import com.e2.medicalequipment.dto.UpdateCustomerDTO;
import com.e2.medicalequipment.dto.UpdateItemDTO;
import com.e2.medicalequipment.model.*;
import com.e2.medicalequipment.repository.ContractRepository;
import com.e2.medicalequipment.repository.EquipmentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ContractServiceImpl implements ContractService{

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Override
    public Contract Create(ContractDTO contractDTO) throws Exception {
        Contract contract = new Contract(contractDTO);
        Equipment equipment = equipmentRepository.findByName(contractDTO.equipment).orElseThrow(() -> new EntityNotFoundException("Equipment not found"));
        contract.setEquipment(equipment);
        if (contractRepository.findByHospital(contract.getHospital()) != null) {
            Update(contractDTO);
        }
        Contract savedContract = contractRepository.save(contract);
        return savedContract;
    }
    public Contract Update(ContractDTO contractDTO) throws Exception{
        Contract contract = new Contract(contractDTO);
        Equipment equipment = equipmentRepository.findByName(contractDTO.equipment).orElseThrow(() -> new EntityNotFoundException("Equipment not found"));
        contract.setEquipment(equipment);
        Contract savedContract = contractRepository.save(contract);
        return savedContract;
    }
    @Override
    public List<ContractDTO> GetAll() throws Exception {
        List<ContractDTO> allContracts = new ArrayList<>();
        for(Contract c : contractRepository.findAll()) {
            allContracts.add(new ContractDTO(c));
        }
        return allContracts;
    }
}
