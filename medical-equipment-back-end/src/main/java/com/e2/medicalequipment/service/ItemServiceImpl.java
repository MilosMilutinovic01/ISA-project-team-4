package com.e2.medicalequipment.service;

import com.e2.medicalequipment.dto.CreateItemDTO;
import com.e2.medicalequipment.model.Company;
import com.e2.medicalequipment.model.Customer;
import com.e2.medicalequipment.model.Equipment;
import com.e2.medicalequipment.model.Item;
import com.e2.medicalequipment.repository.CompanyRepository;
import com.e2.medicalequipment.repository.CustomerRepository;
import com.e2.medicalequipment.repository.EquipmentRepository;
import com.e2.medicalequipment.repository.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService{
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CompanyRepository companyRepository;
    @Override
    public Item Create(CreateItemDTO itemDto) throws Exception {
        Item item = new Item(itemDto);
        item.setAppointment(null);

        Equipment equipment = equipmentRepository.findById(itemDto.equipment.getId()).orElseThrow(() -> new EntityNotFoundException("Equipment not found"));
        item.setEquipment(equipment);

        Customer customer = customerRepository.findById(itemDto.customerId).orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        item.setCustomer(customer);

        Company company = companyRepository.findById(itemDto.companyId).orElseThrow(() -> new EntityNotFoundException("Company not found"));
        item.setCompany(company);

        if (item.getId() != null) {
            throw new Exception("ID must be null for a new entity.");
        }

        Item savedItem = itemRepository.save(item);
        return savedItem;
    }

    @Override
    public List<Item> GetAllByCustomerId(String customerId) throws Exception {
        return itemRepository.findAllByCustomerId(customerId);
    }

    @Override
    public Customer GetCustomerByAppointmentId(String appointmentId) throws Exception {
        List<Item> items = itemRepository.findAllByAppointmentId(appointmentId);
        return items.get(0).getCustomer();
    }

}
