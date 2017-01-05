package com.inobil.fraudwatcher.dal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.inobil.fraudwatcher.entity.ApplicationRule;

public interface ApplicationRuleRepository extends JpaRepository<ApplicationRule, Integer> {
	@Query("select ar from ApplicationRule ar where ar.applicationId = ?1 and ar.isActive=1")
	List<ApplicationRule> findByApplicationId(String applicationId);
}
