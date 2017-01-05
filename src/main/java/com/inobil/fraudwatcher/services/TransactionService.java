package com.inobil.fraudwatcher.services;

import java.util.List;

import com.inobil.fraudwatcher.entity.Transaction;

public interface TransactionService {
	List<Transaction> findByApplicationId(String applicationId);
	List<Transaction> findAll();
	void save(Transaction transaction);
}