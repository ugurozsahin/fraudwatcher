package com.inobil.fraudwatcher.rules;

import com.inobil.fraudwatcher.ContextProvider;
import com.inobil.fraudwatcher.dal.DbRepositoryExtensions;
import com.inobil.fraudwatcher.entity.ApplicationRule;

public class RuleSqlClause extends RuleBase {
	
	private DbRepositoryExtensions repository;
	   
	public RuleSqlClause()
	{
		repository = (DbRepositoryExtensions)ContextProvider.getBean("dbRepositoryExtensionsImpl");
	}
    
	@Override
	public Boolean RuleHandle(TransactionContext transactionContext, ApplicationRule applicationRule){
		String sqlClause = transactionContext.InitializeSqlClause(applicationRule.getValue());
		Boolean result = repository.Check(sqlClause);
		return result;
	}

}
