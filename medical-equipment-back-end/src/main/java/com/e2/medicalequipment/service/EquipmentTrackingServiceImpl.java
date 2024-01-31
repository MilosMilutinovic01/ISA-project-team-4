package com.e2.medicalequipment.service;

import com.e2.medicalequipment.dto.CreateCompanyDTO;
import com.e2.medicalequipment.dto.EquipmentTrackingDTO;
import com.e2.medicalequipment.dto.UpdateCompanyDTO;
import com.e2.medicalequipment.model.*;
import com.e2.medicalequipment.repository.CompanyRepository;
import com.e2.medicalequipment.repository.EquipmentRepository;
import com.e2.medicalequipment.repository.EquipmentTrackingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
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

    @Override
    public EquipmentTracking FindByCompanyAndEquipment(long companyId, long equipmentId) throws Exception {
        List<EquipmentTrackingDTO> result = new ArrayList<>();
        return equipmentTrackingRepository.findByCompanyAndEquipmentId(companyId, equipmentId);
    }

    public EquipmentTracking Get(String id) {
        return equipmentTrackingRepository.findById(Long.valueOf(id)).get();
    }
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public EquipmentTracking Update(EquipmentTrackingDTO dto){
        try {
            EquipmentTracking equipmentTrackingToUpdate = equipmentTrackingRepository.findOneById(dto.id);
            equipmentTrackingToUpdate.setEquipment(new Equipment(dto.equipment));
            equipmentTrackingToUpdate.setCompany(new Company(dto.company));
            equipmentTrackingToUpdate.setCount(dto.count);

            if (equipmentTrackingToUpdate.getId() == null) {
                throw new IllegalArgumentException("ID must not be null for updating entity.");
            }

            EquipmentTracking savedEquipmentTracking = equipmentTrackingRepository.save(equipmentTrackingToUpdate);
            return savedEquipmentTracking;
        }catch(PessimisticLockingFailureException e){
            throw e;
        }catch(Exception e){
            throw e;
        }
    }

    @Transactional(readOnly = false)
    public EquipmentTracking findOneById(long id) {
        EquipmentTracking e = equipmentTrackingRepository.findOneById(id);
        return e;
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
