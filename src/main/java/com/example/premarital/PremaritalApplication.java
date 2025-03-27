package com.example.premarital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PremaritalApplication {

	public static void main(String[] args) {
		SpringApplication.run(PremaritalApplication.class, args);
	}

}
