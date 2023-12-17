package com.e2.medicalequipment.service;

import com.e2.medicalequipment.dto.CreateItemDTO;
import com.e2.medicalequipment.model.Customer;
import com.e2.medicalequipment.model.Equipment;
import com.e2.medicalequipment.model.Item;
import com.e2.medicalequipment.repository.CustomerRepository;
import com.e2.medicalequipment.repository.EquipmentRepository;
import com.e2.medicalequipment.repository.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ItemServiceImpl implements ItemService{
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public Item Create(CreateItemDTO itemDto) throws Exception {
        Item item = new Item(itemDto);
        item.setAppointment(null);

        Equipment equipment = equipmentRepository.findById(itemDto.equipment.getId()).orElseThrow(() -> new EntityNotFoundException("Equipment not found"));
        item.setEquipment(itemDto.equipment);

        Customer customer = customerRepository.findById(itemDto.customerId).orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        item.setCustomer(customer);

        if (item.getId() != null) {
            throw new Exception("ID must be null for a new entity.");
        }

        Item savedItem = itemRepository.save(item);
        return savedItem;
    }
}
