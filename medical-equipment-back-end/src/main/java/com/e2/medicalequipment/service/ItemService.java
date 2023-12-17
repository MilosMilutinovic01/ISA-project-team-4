package com.e2.medicalequipment.service;


import com.e2.medicalequipment.dto.CreateItemDTO;
import com.e2.medicalequipment.model.Item;

import java.util.List;

public interface ItemService {
    Item Create(CreateItemDTO itemDto) throws Exception;
    List<Item> GetAllByCustomerId(String customerId) throws Exception;
}
