package com.e2.medicalequipment.service;

import com.e2.medicalequipment.dto.LatLngDTO;
import com.fasterxml.jackson.databind.ObjectMapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import java.util.List;

@Service
public class SimulatorServiceImpl implements SimulatorService {

    @Autowired
    private KafkaListenerEndpointRegistry registry;
    public SimulatorServiceImpl() {
    }

    public void StartSimulator(List<LatLngDTO> coordinates, String forUser, String refreshRate)
    {
        StartKafka();
        try {
            registry.getListenerContainer("simulatorListener").start();
            ProcessBuilder processBuilder = new ProcessBuilder("python", "simulator.py",forUser,refreshRate);
            Process process = processBuilder.start();
            System.out.println("Simulator started successfully!");
            OutputStream outputStream = process.getOutputStream();
            Writer writer = new OutputStreamWriter(outputStream);
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonData = objectMapper.writeValueAsString(coordinates);
            writer.write(jsonData);
            writer.flush();
            System.out.println("Coordinate data sent to simulator!");
            writer.close();
            int exitCode = process.waitFor();
            if(exitCode == 0)
            {
                System.out.println("Simulator finished successfully! exit_code="+exitCode);
                EndSimulator();
            }else{
                System.out.println("Simulator exited with an error! exit_code="+exitCode);
                EndSimulator();
            }
        } catch (IOException | InterruptedException e) {
            EndSimulator();
            e.printStackTrace();
        }
    }

    private void StartKafka(){
        try {

            ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c/kafka", "start_kafka.bat");
            Process process = processBuilder.start();
            int exitCode = process.waitFor();
            System.out.println("Kafka startup process exited with code: " + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void EndSimulator(){
        registry.getListenerContainer("simulatorListener").stop();
    }


}
