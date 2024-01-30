package com.e2.medicalequipment.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ContractConsumer {

    @KafkaListener(topics = "contract", groupId = "contract")
    public void listenContract(String message) {
        System.out.println("Received Message in group foo: " + message);
    }
}
