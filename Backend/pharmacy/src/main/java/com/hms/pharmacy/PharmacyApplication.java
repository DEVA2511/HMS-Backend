package com.hms.pharmacy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients
@EnableScheduling // Enable scheduling for periodic tasks like inventory checks this will used to for the delete the expried medicines from the inventory
public class PharmacyApplication {

	public static void main(String[] args) {
		SpringApplication.run(PharmacyApplication.class, args);
	}

}
