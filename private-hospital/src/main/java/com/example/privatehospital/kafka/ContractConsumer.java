package com.example.privatehospital.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ContractConsumer {

    @KafkaListener(topics = "contractNotif", groupId = "contractNotif", containerFactory = "kafkaListenerContainerFactory")
    public void listenContract(String message) {
        System.out.println("---------------- Received Message ---------------------");
        System.out.println(message);
    }
}
