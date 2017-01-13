package com.inobil.fraudwatcher;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.inobil.fraudwatcher.dal.DbRepositoryExtensions;
import com.inobil.fraudwatcher.dal.DbRepositoryExtensionsImpl;
import com.inobil.fraudwatcher.rest.ApplicationRest;
import com.inobil.fraudwatcher.rules.RuleBase;

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
	
	@Bean
	public DbRepositoryExtensions dbRepositoryExtensions() {
	    return new DbRepositoryExtensionsImpl();
	}
}