package com.e2.medicalequipment.service;


import com.e2.medicalequipment.dto.CreateItemDTO;
import com.e2.medicalequipment.model.Item;

public interface ItemService {
    Item Create(CreateItemDTO itemDto) throws Exception;
}
