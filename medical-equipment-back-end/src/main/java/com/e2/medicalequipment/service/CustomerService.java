package com.e2.medicalequipment.service;

import com.e2.medicalequipment.dto.CreateCustomerDTO;
import com.e2.medicalequipment.dto.UpdateCustomerDTO;
import com.e2.medicalequipment.model.Customer;

public interface CustomerService {
    Customer Create(CreateCustomerDTO test) throws Exception;
    Customer Update(UpdateCustomerDTO customerDTO) throws Exception;
    Customer Get(String id) throws Exception;
}
