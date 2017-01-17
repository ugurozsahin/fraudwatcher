package com.inobil.fraudwatcher.rest;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;

import com.inobil.fraudwatcher.entity.Transaction;
import com.inobil.fraudwatcher.rules.RuleEngine;
import com.inobil.fraudwatcher.rules.TransactionContext;
import com.inobil.fraudwatcher.services.TransactionService;

import io.swagger.annotations.*;
import io.swagger.annotations.ApiResponse;

@Api(value = "api/v1/transaction", tags = "transaction")
@RestController
@RequestMapping("api/v1/transaction")
public class TransactionController {
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

	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Transaction> getTransactions() {
		return transactionService.findAll();
	}
	
    @ApiOperation(value = "createTransaction", nickname = "createTransaction")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "transaction", value = "Payment Transaction", required = true, dataType = "string", paramType = "body")
      })
    @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Success", response = TransactionContext.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")}) 
	@RequestMapping(value = "", method = RequestMethod.POST)
	public TransactionContext createTransaction(@RequestBody Transaction transaction) {
		ruleEngine.Initialize(transaction);
		ruleEngine.Execute();
		return ruleEngine.getTransactionContext();
	}

    @RequestMapping(value = "/getbyapplicationid", method = RequestMethod.GET)	
	public List<Transaction> getApplication(@RequestParam("id") String applicationId) {
		return transactionService.findByApplicationId(applicationId);
	}
}