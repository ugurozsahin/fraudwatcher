package com.inobil.fraudwatcher.services;

import java.util.List;

import com.inobil.fraudwatcher.entity.Application;

public interface ApplicationService {
	Application findByApplicationId(String applicationId);
	List<Application> findAll();
}