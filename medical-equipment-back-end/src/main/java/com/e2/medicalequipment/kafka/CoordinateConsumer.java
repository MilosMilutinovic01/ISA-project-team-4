package com.e2.medicalequipment.kafka;

import com.e2.medicalequipment.dto.KafkaCoords;
import com.e2.medicalequipment.dto.LatLngDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class CoordinateConsumer {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @KafkaListener(id="simulatorListener",topics = "simulator", groupId = "simulator",
            containerFactory = "kafkaListenerContainerFactory",autoStartup = "false")
    public void listenSimulator(String message)
    {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            KafkaCoords coords = objectMapper.readValue(message, KafkaCoords.class);
            LatLngDTO latLngDTO = new LatLngDTO(coords.getLat(),coords.getLng());
            simpMessagingTemplate.convertAndSendToUser(coords.getUser(),"/queue/simulator", latLngDTO);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}