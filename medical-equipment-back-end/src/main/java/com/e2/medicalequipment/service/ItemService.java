package com.e2.medicalequipment.service;


import com.e2.medicalequipment.dto.CreateItemDTO;
import com.e2.medicalequipment.model.Customer;
import com.e2.medicalequipment.dto.UpdateCompanyAdministratorDTO;
import com.e2.medicalequipment.dto.UpdateItemDTO;
import com.e2.medicalequipment.model.CompanyAdministrator;
import com.e2.medicalequipment.model.Item;

import java.util.List;

public interface ItemService {
    Item Create(CreateItemDTO itemDto) throws Exception;
    boolean Delete(Long id) throws Exception;
    List<Item> GetAllByCustomerId(String customerId) throws Exception;
    Customer GetCustomerByAppointmentId(String appointmentId) throws Exception;
    Item Get(Long id) throws Exception;
    List<Item> GetAllByAppointmentId(String id) throws Exception;
    Item Update(UpdateItemDTO item) throws Exception;

    Item Process(Item item,Boolean pickedUp) throws Exception;

}
