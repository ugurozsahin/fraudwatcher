package com.inobil.fraudwatcher.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.inobil.fraudwatcher.entity.Application;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {
	@Query("select a from Application a where a.applicationId = ?1")
	Application findByApplicationId(String applicationId);
}
