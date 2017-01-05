package com.inobil.fraudwatcher.rest;

import java.util.List;

import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.inobil.fraudwatcher.entity.Application;
import com.inobil.fraudwatcher.services.ApplicationService;

@Named
@Path("/application")
public class ApplicationRest {
	private ApplicationService applicationService;
	
	/**
	 * @param applicationService the applicationService to set
	 */
	@Autowired
	public void setApplicationService(ApplicationService applicationService) {
		this.applicationService = applicationService;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Application> getApplications() {
		return applicationService.findAll();
	}

	@GET
	@Path("getbyapplicationid")
	@Produces(MediaType.APPLICATION_JSON)
	public Application getApplication(@QueryParam("id") String applicationId) {
		return applicationService.findByApplicationId(applicationId);
	}
}