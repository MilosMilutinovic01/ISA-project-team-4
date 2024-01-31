package com.e2.medicalequipment.kafka;

import com.e2.medicalequipment.dto.ContractDTO;
import com.e2.medicalequipment.dto.KafkaCoords;
import com.e2.medicalequipment.dto.LatLngDTO;
import com.e2.medicalequipment.service.ContractService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ContractConsumer {
    @Autowired
    private ContractService contractService;
    @KafkaListener(topics = "contract", groupId = "contract", containerFactory = "kafkaListenerContainerFactory")
    public void listenContract(String message) {
        try {
            System.out.println("---------------- Received Message ---------------------");
            ObjectMapper objectMapper = new ObjectMapper();
            ContractDTO contract = objectMapper.readValue(message, ContractDTO.class);
            System.out.println("Hospital: "+ contract.hospital);
            System.out.println("Equipment: " + contract.equipment);
            System.out.println("Count: "+ contract.count);
            contractService.Create(contract);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
