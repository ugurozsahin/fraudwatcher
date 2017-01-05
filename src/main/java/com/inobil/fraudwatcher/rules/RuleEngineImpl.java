package com.inobil.fraudwatcher.rules;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inobil.fraudwatcher.entity.Application;
import com.inobil.fraudwatcher.entity.ApplicationRule;
import com.inobil.fraudwatcher.entity.Transaction;
import com.inobil.fraudwatcher.loaders.ApplicationLoader;
import com.inobil.fraudwatcher.services.ApplicationRuleService;
import com.inobil.fraudwatcher.services.ApplicationService;
import com.inobil.fraudwatcher.services.TransactionService;

@Service
public class RuleEngineImpl implements RuleEngine {
	private ApplicationService applicationService;
	private ApplicationRuleService applicationRuleService;
	private TransactionService transactionService;	
	private Application application;
	private List<ApplicationRule> applicationRules;
	private TransactionContext transactionContext;

	/**
	 * @param applicationService the applicationService to set
	 */
	@Autowired
	public void setApplicationService(ApplicationService applicationService) {
		this.applicationService = applicationService;
	}

	/**
	 * @param applicationRuleService the applicationRuleService to set
	 */
	@Autowired
	public void setApplicationRuleService(ApplicationRuleService applicationRuleService) {
		this.applicationRuleService = applicationRuleService;
	}
	
	/**
	 * @param transactionService the transactionService to set
	 */
	@Autowired
	public void setTransactionService(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	private Logger log = Logger.getLogger(ApplicationLoader.class);

	/* (non-Javadoc)
	 * @see com.inobil.fraudwatcher.rules.RuleEngine#Initialize(com.inobil.fraudwatcher.entity.Transaction)
	 */
	@Override
	public void Initialize(Transaction transaction)
	{
		String applicationId = transaction.getApplicationId();
		setApplication(applicationService.findByApplicationId(applicationId));
		setApplicationRules(applicationRuleService.findByApplicationId(applicationId));
		
		setTransactionContext(new TransactionContext(transaction));
	}
    
	/* (non-Javadoc)
	 * @see com.inobil.fraudwatcher.rules.RuleEngine#Execute()
	 */
	@Override
	public void Execute()
	{
		for (ApplicationRule applicationRule : applicationRules) {
			String ruleTypeName = String.format("%1$s.Rule%2$s", this.getClass().getPackage().getName(), applicationRule.getRuleType().toString());
			RuleBase ruleBase = null;
			try {
				ruleBase = (RuleBase)Class.forName(ruleTypeName).newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
			
			if(ruleBase != null)
			{
				Boolean result = ruleBase.Handle(transactionContext, applicationRule);
				if(result)
				{
					double totalFraudLimit = transactionContext.getTotalFraudPoint() + applicationRule.getPoint();
					transactionContext.setTotalFraudPoint(totalFraudLimit);
					transactionContext.addCatchedRule(applicationRule);
					if(getApplication().getFraudLimit() <= transactionContext.getTotalFraudPoint())
					{
						transactionContext.setIsFraud(true);
						break;
					}
				}
			}
		}
		transactionService.save(transactionContext.getTransaction());
	}

	/**
	 * @return the context
	 */
	public TransactionContext getTransactionContext() {
		return transactionContext;
	}

	/**
	 * @param context the context to set
	 */
	public void setTransactionContext(TransactionContext transactionContext) {
		this.transactionContext = transactionContext;
	}

	/**
	 * @return the applicationRules
	 */
	public List<ApplicationRule> getApplicationRules() {
		return applicationRules;
	}

	/**
	 * @param applicationRules the applicationRules to set
	 */
	public void setApplicationRules(List<ApplicationRule> applicationRules) {
		this.applicationRules = applicationRules;
	}

	/**
	 * @return the application
	 */
	public Application getApplication() {
		return application;
	}

	/**
	 * @param application the application to set
	 */
	public void setApplication(Application application) {
		this.application = application;
	}
}
