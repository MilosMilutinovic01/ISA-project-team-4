package com.e2.medicalequipment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class MedicalEquipmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicalEquipmentApplication.class, args);
	}

}
