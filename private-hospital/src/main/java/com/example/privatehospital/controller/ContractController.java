package com.example.privatehospital.controller;

import com.example.privatehospital.dto.ContractDTO;
import com.example.privatehospital.kafka.ContractProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/contract", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class ContractController {

    @Autowired
    public ContractProducer contractProducer;

    @PostMapping(value = "/send", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String sendMessage(@RequestBody ContractDTO contractDTO) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonContract = objectMapper.writeValueAsString(contractDTO);
            contractProducer.sendMessage(jsonContract);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "Message sent for: " + contractDTO.hospital;
    }
}
