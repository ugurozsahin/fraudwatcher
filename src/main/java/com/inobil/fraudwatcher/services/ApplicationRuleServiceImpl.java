package com.inobil.fraudwatcher.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inobil.fraudwatcher.dal.ApplicationRuleRepository;
import com.inobil.fraudwatcher.entity.ApplicationRule;

@Service
public class ApplicationRuleServiceImpl implements ApplicationRuleService {
	private ApplicationRuleRepository applicationRuleRepository;

	/**
	 * @param applicationRuleRepository the applicationRuleRepository to set
	 */
	@Autowired
	public void setApplicationRuleRepository(ApplicationRuleRepository applicationRuleRepository) {
		this.applicationRuleRepository = applicationRuleRepository;
	}

	@Override
	public List<ApplicationRule> findByApplicationId(String applicationId) {
		return applicationRuleRepository.findByApplicationId(applicationId);
	}

	@Override
	public List<ApplicationRule> findAll() {
		return applicationRuleRepository.findAll();
	}
}