package com.e2.medicalequipment.service;

import com.e2.medicalequipment.dto.CreateCustomerDTO;
import com.e2.medicalequipment.dto.UpdateCustomerDTO;
import com.e2.medicalequipment.dto.UserTokenState;
import com.e2.medicalequipment.model.Customer;

public interface CustomerService {
    Customer Create(CreateCustomerDTO test);
    Customer Update(UpdateCustomerDTO customerDTO) throws Exception;
    Customer Get(String id) throws Exception;
    Customer findByVerificationToken(String id) throws Exception;
    Customer GivePenaltyPoints(Long id) throws Exception;
    Customer findByUsername(String username);
}
