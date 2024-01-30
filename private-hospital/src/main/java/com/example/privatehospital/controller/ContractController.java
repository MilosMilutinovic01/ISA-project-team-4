package com.example.privatehospital.controller;

import com.example.privatehospital.kafka.ContractProducer;
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

    @PostMapping("/send")
    public String sendMessage(@RequestParam("message") String message) {
        contractProducer.sendMessage(message);
        return "Message sent: " + message;
    }
}
