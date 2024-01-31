package com.e2.medicalequipment.service;


import com.e2.medicalequipment.dto.EquipmentTrackingDTO;
import com.e2.medicalequipment.model.EquipmentTracking;

import java.util.List;
import java.util.Set;

public interface EquipmentTrackingService {
    List<EquipmentTrackingDTO> GetAll() throws Exception;
    List<EquipmentTrackingDTO> GetWanted(String searchName,String type) throws Exception;
    EquipmentTracking FindByCompanyAndEquipment(long companyId, long equipmentId) throws Exception;
    EquipmentTracking Update(EquipmentTrackingDTO equipmentTrackingDTO);
    EquipmentTracking Get(String id) throws Exception;
    List<EquipmentTrackingDTO>  GetByEquipmentId(String id) throws Exception;
    EquipmentTracking findOneById(long id) ;
}
