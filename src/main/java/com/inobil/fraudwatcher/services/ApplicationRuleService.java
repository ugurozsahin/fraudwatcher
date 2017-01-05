package com.inobil.fraudwatcher.services;

import java.util.List;

import com.inobil.fraudwatcher.entity.ApplicationRule;

public interface ApplicationRuleService {
	List<ApplicationRule> findByApplicationId(String applicationId);
	List<ApplicationRule> findAll();
}