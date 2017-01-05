package com.inobil.fraudwatcher.loaders;

import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.inobil.fraudwatcher.dal.ApplicationRepository;
import com.inobil.fraudwatcher.entity.Application;

@Component
public class ApplicationLoader implements ApplicationListener<ContextRefreshedEvent> {
 
	public static String[] ApplicationIdList = new String[] {
			"6436ca99-7b1b-459e-8195-ade3ca6250be",
			"ef49e2cb-3933-49dc-a15e-8d9fd6f43283",
			"38493d89-e128-43d2-971d-b5f6f2836cc0",
			UUID.randomUUID().toString(),
	};
	
    private ApplicationRepository applicationRepository;
 
    private Logger log = Logger.getLogger(ApplicationLoader.class);
 
    @Autowired
    public void setProductRepository(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }
 
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
    	String firstApplicationId = ApplicationIdList[0];
    	String secondApplicationId = ApplicationIdList[1];
    	String thirdApplicationId = ApplicationIdList[2];
    	
    	CreateApplication(firstApplicationId,"First Application", 7D);
    	CreateApplication(secondApplicationId,"Second Application", 5D);
    	CreateApplication(thirdApplicationId,"Third Application", 5D);
    }
    
    public void CreateApplication(String applicationId, String name, double fraudLimit)
    {
    	Application application = new Application();
    	application.setApplicationId(applicationId);
    	application.setName(name);
    	application.setFraudLimit(fraudLimit);
    	application.setIsActive(true);
    	applicationRepository.save(application);
    	
    	CreateLog(application);
    }
    
    public void CreateLog(Application application)
    {
    	log.info("Saved Application - id: " + application.getId());
    }
}