package com.inobil.fraudwatcher.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inobil.fraudwatcher.dal.ApplicationRepository;
import com.inobil.fraudwatcher.entity.Application;

@Service
public class ApplicationServiceImpl implements ApplicationService {
	private ApplicationRepository applicationRepository;

	/**
	 * @param applicationRepository the applicationRepository to set
	 */
	@Autowired
	public void setApplicationRepository(ApplicationRepository applicationRepository) {
		this.applicationRepository = applicationRepository;
	}

	@Override
	public Application findByApplicationId(String applicationId) {
		return applicationRepository.findByApplicationId(applicationId);
	}

	@Override
	public List<Application> findAll() {
		return applicationRepository.findAll();
	}
}