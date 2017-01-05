package com.inobil.fraudwatcher.dal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.inobil.fraudwatcher.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
	@Query("select t from Transaction t where t.applicationId = ?1")
	List<Transaction> findByApplicationId(String applicationId);
}
