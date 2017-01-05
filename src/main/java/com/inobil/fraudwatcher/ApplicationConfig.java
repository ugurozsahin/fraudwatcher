package com.inobil.fraudwatcher;

import javax.inject.Named;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.inobil.fraudwatcher.rest.ApplicationRest;

//@Configuration
//@EnableAutoConfiguration
//@EntityScan(basePackages = {"com.inobil.fraudwatcher"})
//@EnableJpaRepositories(basePackages = {"com.inobil.fraudwatcher"})
//@EnableTransactionManagement
@Configuration
public class ApplicationConfig {
	@Named
	static class JerseyConfig extends ResourceConfig {
		public JerseyConfig() {
			this.packages(ApplicationRest.class.getPackage().getName());
		}
	}
	
	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate;
	}
}