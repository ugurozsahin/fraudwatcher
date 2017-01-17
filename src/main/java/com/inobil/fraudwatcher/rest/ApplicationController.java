package com.inobil.fraudwatcher.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inobil.fraudwatcher.entity.Application;
import com.inobil.fraudwatcher.services.ApplicationService;

@RestController
@RequestMapping("api/v1/application")
public class ApplicationController {
	private ApplicationService applicationService;
	
	/**
	 * @param applicationService the applicationService to set
	 */
	@Autowired
	public void setApplicationService(ApplicationService applicationService) {
		this.applicationService = applicationService;
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Application> getApplications() {
		return applicationService.findAll();
	}

	@RequestMapping(value = "/getbyapplicationid", method = RequestMethod.GET)	
	public Application getApplication(@RequestParam("id") String applicationId) {
		return applicationService.findByApplicationId(applicationId);
	}
}