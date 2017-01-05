package com.inobil.fraudwatcher.rest;

import java.util.List;

import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.inobil.fraudwatcher.entity.Transaction;
import com.inobil.fraudwatcher.rules.RuleEngine;
import com.inobil.fraudwatcher.rules.TransactionContext;
import com.inobil.fraudwatcher.services.ApplicationService;
import com.inobil.fraudwatcher.services.TransactionService;

@Named
@Path("/transaction")
public class TransactionRest {
	private TransactionService transactionService;
	private RuleEngine ruleEngine;
	/**
	 * @param transactionService the transactionService to set
	 */
	@Autowired
	public void setApplicationService(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	/**
	 * @param ruleEngine the ruleEngine to set
	 */
	@Autowired
	public void setRuleEngine(RuleEngine ruleEngine) {
		this.ruleEngine = ruleEngine;
	}

	/**
	 * @param applicationRuleService the applicationRuleService to set
	 */
	@Autowired
	public void setApplicationService(ApplicationService applicationService) {
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Transaction> getApplications() {
		return transactionService.findAll();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public TransactionContext createTransaction(Transaction transaction) {
		ruleEngine.Initialize(transaction);
		ruleEngine.Execute();
		return ruleEngine.getTransactionContext();
	}

	@GET
	@Path("getbyapplicationid")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Transaction> getApplication(@QueryParam("id") String applicationId) {
		return transactionService.findByApplicationId(applicationId);
	}
}