package com.inobil.fraudwatcher.rules;

import com.inobil.fraudwatcher.entity.ApplicationRule;

public class RuleRegexNotMatch extends RuleRegex {

	@Override
	public Boolean RuleHandle(TransactionContext transactionContext, ApplicationRule applicationRule){
		return !super.RuleHandle(transactionContext, applicationRule);
	}

}
