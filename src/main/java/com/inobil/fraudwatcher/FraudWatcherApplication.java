package com.inobil.fraudwatcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class FraudWatcherApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(FraudWatcherApplication.class, args);
	
	}
}
