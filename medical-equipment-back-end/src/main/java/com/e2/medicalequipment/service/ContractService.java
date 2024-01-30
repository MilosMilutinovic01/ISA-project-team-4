package com.e2.medicalequipment.service;

import com.e2.medicalequipment.dto.*;
import com.e2.medicalequipment.model.Contract;
import com.e2.medicalequipment.model.Item;

import java.util.List;

public interface ContractService {

    Contract Create(ContractDTO contractDTO) throws Exception;
    Contract Update(ContractDTO contractDTO) throws Exception;
    List<Contract> GetAll() throws Exception;
    Contract UpdateCancellation(String hospital, boolean cancel) throws Exception;

}
