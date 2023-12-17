package com.e2.medicalequipment.service;


import com.e2.medicalequipment.dto.ItemDTO;
import com.e2.medicalequipment.model.Item;

public interface ItemService {
    Item Create(ItemDTO itemDto) throws Exception;
}
