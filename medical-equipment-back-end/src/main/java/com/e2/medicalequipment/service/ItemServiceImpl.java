package com.e2.medicalequipment.service;

import com.e2.medicalequipment.dto.CreateItemDTO;
import com.e2.medicalequipment.model.Item;
import com.e2.medicalequipment.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ItemServiceImpl implements ItemService{
    @Autowired
    private ItemRepository itemRepository;
    @Override
    public Item Create(CreateItemDTO itemDto) throws Exception {
        Item item = new Item(itemDto);
        item.setAppointment(null);

        if (item.getId() != null) {
            throw new Exception("ID must be null for a new entity.");
        }

        Item savedItem = itemRepository.save(item);
        return savedItem;
    }
}
