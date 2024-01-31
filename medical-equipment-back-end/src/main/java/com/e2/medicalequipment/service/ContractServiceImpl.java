package com.e2.medicalequipment.service;

import com.e2.medicalequipment.dto.*;
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
        Contract savedContract = null;
        if(equipmentRepository.findByName(contractDTO.equipment) != null) {
            Equipment equipment = equipmentRepository.findByName(contractDTO.equipment);
            contract.setEquipment(equipment);
            if (contractRepository.findByHospital(contract.getHospital()) != null) {
                Update(contractDTO);
            }
            savedContract = contractRepository.save(contract);
        }
        return savedContract;
    }
    public Contract Update(ContractDTO contractDTO) throws Exception{
        Contract contract = contractRepository.findByHospital(contractDTO.hospital);
        Contract savedContract = null;
        if(equipmentRepository.findByName(contractDTO.equipment) != null) {
            Equipment equipment = equipmentRepository.findByName(contractDTO.equipment);
            contract.setEquipment(equipment);
            contract.setCount(contractDTO.count);
            contract.setDateInMonth(contractDTO.dateInMonth);
            contract.setCanceledThisMonth(contract.getCanceledThisMonth());
            savedContract = contractRepository.save(contract);
        }
        return savedContract;
    }
    @Override
    public Contract UpdateCancellation(String hospital, boolean cancel) throws Exception{
        Contract contract = contractRepository.findByHospital(hospital);
        contract.setCanceledThisMonth(cancel);
        Contract savedContract = contractRepository.save(contract);
        return savedContract;
    }
    @Override
    public List<Contract> GetAll() throws Exception {
        List<Contract> allContracts = new ArrayList<>();
        for(Contract c : contractRepository.findAll()) {
            allContracts.add(c);
        }
        return allContracts;
    }
}
