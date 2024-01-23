package com.e2.medicalequipment.service;

import com.e2.medicalequipment.dto.CreateItemDTO;
import com.e2.medicalequipment.dto.UpdateCompanyAdministratorDTO;
import com.e2.medicalequipment.dto.UpdateItemDTO;
import com.e2.medicalequipment.model.*;
import com.e2.medicalequipment.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

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

    @Autowired
    private AppointmentRepository appointmentRepository;
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
        if (items.isEmpty()) {
            throw new Exception("No items found for the given appointment ID.");
        }
        return items.get(0).getCustomer();
    }

    public Item Get(Long id) throws Exception{
        return this.itemRepository.findById(id).get();
    }
    public Item Update(UpdateItemDTO newItem) throws Exception{
        Item item = itemRepository.findById(newItem.Id);
        item.setAppointment(appointmentRepository.findById(newItem.AppointmentId));
        if ((item.getId() == null)){
            throw new Exception("ID must not be null for updating entity.");
        }
        Item savedItem = itemRepository.save(item);
        return savedItem;
    }
}
