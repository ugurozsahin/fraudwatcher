package com.inobil.fraudwatcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.inobil.fraudwatcher.dal.DbRepositoryExtensions;
import com.inobil.fraudwatcher.dal.DbRepositoryExtensionsImpl;

@SpringBootApplication
public class FraudWatcherApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(FraudWatcherApplication.class, args);
	
	}
	
	@Bean
	public DbRepositoryExtensions dbRepositoryExtensions() {
	    return new DbRepositoryExtensionsImpl();
	}
}
