package com.inobil.fraudwatcher.rest;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;

import com.inobil.fraudwatcher.entity.ApplicationRule;
import com.inobil.fraudwatcher.services.ApplicationRuleService;

@RestController
@RequestMapping("api/v1/applicationrule")
public class ApplicationRuleController {
	private ApplicationRuleService applicationRuleService;
	
	/**
	 * @param applicationService the applicationService to set
	 */
	@Autowired
	public void setApplicationRuleService(ApplicationRuleService applicationRuleService) {
		this.applicationRuleService = applicationRuleService;
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<ApplicationRule> getApplicationRules() {
		return applicationRuleService.findAll();
	}

	@RequestMapping(value = "/getbyapplicationid", method = RequestMethod.GET)	
	public List<ApplicationRule> getApplicationRulesByApplicationId(@RequestParam("id") String applicationId) {
		return applicationRuleService.findByApplicationId(applicationId);
	}
}