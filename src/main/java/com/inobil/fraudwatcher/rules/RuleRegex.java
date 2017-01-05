package com.inobil.fraudwatcher.rules;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.inobil.fraudwatcher.entity.ApplicationRule;

public class RuleRegex extends RuleBase {

	@Override
	public Boolean RuleHandle(TransactionContext transactionContext, ApplicationRule applicationRule){
		String regexPattern = applicationRule.getValue();
		Pattern regex = Pattern.compile(regexPattern);

		String fieldName = applicationRule.getFieldName();
		String fieldValue = transactionContext.getFieldValue(fieldName);
		Matcher m = regex.matcher(fieldValue);
		return m.find();
	}

}
