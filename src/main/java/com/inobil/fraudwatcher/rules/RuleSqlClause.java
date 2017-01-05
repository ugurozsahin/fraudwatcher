package com.inobil.fraudwatcher.rules;

import com.inobil.fraudwatcher.entity.ApplicationRule;

public class RuleSqlClause extends RuleBase {

	@Override
	public Boolean RuleHandle(TransactionContext transactionContext, ApplicationRule applicationRule){
		String sqlClause = transactionContext.InitializeSqlClause(applicationRule.getValue());
		return false;
	}

}
