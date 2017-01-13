package com.inobil.fraudwatcher.rules;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.inobil.fraudwatcher.entity.ApplicationRule;
import com.inobil.fraudwatcher.entity.Transaction;

import java.util.Set;

public class TransactionContext {
	private double totalFraudPoint;
	private Transaction transaction;
	private ArrayList<ApplicationRule> catchedRuleList = null;
	private HashMap<String, String> fieldValues = new HashMap<String, String>();
	private Boolean isFraud = false;

	public TransactionContext(Transaction transaction) 
	{
		this.transaction = transaction;
		setCatchedRuleList(new ArrayList<ApplicationRule>());
		
		Initialize();
	}
	
	private void Initialize()
	{
		Field[] fields = transaction.getClass().getDeclaredFields();
		for (Field field : fields) {
			try {
				field.setAccessible(true);
				Object fieldValue = field.get(transaction);
				if(fieldValue != null)
					fieldValues.put(field.getName(), fieldValue.toString());
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @return the transaction
	 */
	public Transaction getTransaction() {
		return transaction;
	}

	/**
	 * @param transaction the transaction to set
	 */
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	/**
	 * @return the totalFraudPoint
	 */
	public double getTotalFraudPoint() {
		return totalFraudPoint;
	}

	/**
	 * @param totalFraudPoint the totalFraudPoint to set
	 */
	public void setTotalFraudPoint(double totalFraudPoint) {
		this.totalFraudPoint = totalFraudPoint;
	}

	/**
	 * @param catchedRule the catchedRuleList to add
	 */
	public void addCatchedRule(ApplicationRule catchedRule) {
		this.getCatchedRuleList().add(catchedRule);
	}
	
	public String getFieldValue(String fieldName)
	{
		if(!fieldValues.containsKey(fieldName))
			return null;

		return fieldValues.get(fieldName);
	}
	
	public String InitializeSqlClause(String sqlClause)
	{
		if(fieldValues.isEmpty())
			return sqlClause;
			
		 Set<Entry<String, String>> set = fieldValues.entrySet();
		 for (Entry<String, String> field : set) {
			 String pattern = String.format("#%1$s#", field.getKey().toString());
			 sqlClause = sqlClause.replaceAll(pattern.toString(), field.getValue().toString());
		 }
		 
		return sqlClause;
	}

	/**
	 * @return the catchedRuleList
	 */
	public ArrayList<ApplicationRule> getCatchedRuleList() {
		return catchedRuleList;
	}

	/**
	 * @param catchedRuleList the catchedRuleList to set
	 */
	public void setCatchedRuleList(ArrayList<ApplicationRule> catchedRuleList) {
		this.catchedRuleList = catchedRuleList;
	}

	/**
	 * @return the isFraud
	 */
	public Boolean getIsFraud() {
		return isFraud;
	}

	/**
	 * @param isFraud the isFraud to set
	 */
	public void setIsFraud(Boolean isFraud) {
		this.isFraud = isFraud;
	}
}
