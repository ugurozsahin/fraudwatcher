package com.inobil.fraudwatcher.rules;

import java.math.BigDecimal;

import com.inobil.fraudwatcher.entity.ApplicationRule;

public class RuleAmountBetween extends RuleBase {

	@Override
	public Boolean RuleHandle(TransactionContext transactionContext, ApplicationRule applicationRule){
		String[] values = applicationRule.getValue().split("|");
		BigDecimal firstValue = new BigDecimal(values[0]);
		BigDecimal secondValue = new BigDecimal(values[1]);
		BigDecimal amount = transactionContext.getTransaction().getAmount();
		return amount.compareTo(firstValue) == 1 && amount.compareTo(secondValue) == -1;
	}

}
