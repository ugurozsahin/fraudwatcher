package com.inobil.fraudwatcher.rest;

import java.util.List;

import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.inobil.fraudwatcher.entity.ApplicationRule;
import com.inobil.fraudwatcher.services.ApplicationRuleService;

@Named
@Path("/applicationrule")
public class ApplicationRuleRest {
	private ApplicationRuleService applicationRuleService;
	
	/**
	 * @param applicationService the applicationService to set
	 */
	@Autowired
	public void setApplicationRuleService(ApplicationRuleService applicationRuleService) {
		this.applicationRuleService = applicationRuleService;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ApplicationRule> getApplicationRules() {
		return applicationRuleService.findAll();
	}

	@GET
	@Path("getbyapplicationid")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ApplicationRule> getApplicationRulesByApplicationId(@QueryParam("id") String applicationId) {
		return applicationRuleService.findByApplicationId(applicationId);
	}
}