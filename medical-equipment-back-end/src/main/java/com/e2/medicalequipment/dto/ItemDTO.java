package com.e2.medicalequipment.dto;

import com.e2.medicalequipment.model.Company;
import com.e2.medicalequipment.model.Equipment;

public class ItemDTO {
    public long id;
    public int count;
    public Equipment equipment;
    public UpdateCustomerDTO customer;
    public Company company;
    public boolean pickedUp;
}
