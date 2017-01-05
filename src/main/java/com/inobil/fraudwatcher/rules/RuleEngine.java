package com.inobil.fraudwatcher.rules;

import com.inobil.fraudwatcher.entity.Transaction;

public interface RuleEngine {

	void Initialize(Transaction transaction);

	void Execute();
	
	TransactionContext getTransactionContext();
}