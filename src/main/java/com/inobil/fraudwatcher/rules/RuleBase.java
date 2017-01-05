package com.inobil.fraudwatcher.rules;

import com.inobil.fraudwatcher.entity.ApplicationRule;

public abstract class RuleBase {

	public Boolean Handle(TransactionContext transactionContext, ApplicationRule applicationRule) {
		if(transactionContext == null || applicationRule == null)
			return false;
		
		return RuleHandle(transactionContext, applicationRule);
	};
	
	public abstract Boolean RuleHandle(TransactionContext transactionContext, ApplicationRule applicationRule);
}
