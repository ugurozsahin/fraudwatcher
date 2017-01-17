package com.inobil.fraudwatcher;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import com.inobil.fraudwatcher.dal.DbRepositoryExtensions;
import com.inobil.fraudwatcher.dal.DbRepositoryExtensionsImpl;



//@EnableAutoConfiguration
//@EntityScan(basePackages = {"com.inobil.fraudwatcher"})
//@EnableJpaRepositories(basePackages = {"com.inobil.fraudwatcher"})
//@EnableTransactionManagement
@Configuration
public class ApplicationConfig {

	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate;
	}
	
	@Bean
	public DbRepositoryExtensions dbRepositoryExtensions() {
	    return new DbRepositoryExtensionsImpl();
	}
}