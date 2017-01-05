package com.inobil.fraudwatcher.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inobil.fraudwatcher.dal.TransactionRepository;
import com.inobil.fraudwatcher.entity.Transaction;

@Service
public class TransactionServiceImpl implements TransactionService {
	private TransactionRepository transactionRepository;

	/**
	 * @param transactionRepository the transactionRepository to set
	 */
	@Autowired
	public void setTransactionRepository(TransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
	}

	@Override
	public List<Transaction> findByApplicationId(String applicationId) {
		return transactionRepository.findByApplicationId(applicationId);
	}

	@Override
	public List<Transaction> findAll() {
		return transactionRepository.findAll();
	}
	
	@Override
	public void save(Transaction transaction) {
		transactionRepository.save(transaction);
	}
}