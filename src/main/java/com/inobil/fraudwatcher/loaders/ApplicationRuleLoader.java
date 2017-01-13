package com.inobil.fraudwatcher.loaders;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.inobil.fraudwatcher.dal.ApplicationRuleRepository;
import com.inobil.fraudwatcher.entity.ApplicationRule;
import com.inobil.fraudwatcher.entity.RuleType;

@Component
public class ApplicationRuleLoader implements ApplicationListener<ContextRefreshedEvent> {
 
    private ApplicationRuleRepository applicationRuleRepository;
 
    private Logger log = Logger.getLogger(ApplicationRuleLoader.class);
 
    @Autowired
    public void setProductRepository(ApplicationRuleRepository applicationRuleRepository) {
        this.applicationRuleRepository = applicationRuleRepository;
    }
 
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
    	String firstApplicationId = ApplicationLoader.ApplicationIdList[0];
    	String secondApplicationId = ApplicationLoader.ApplicationIdList[1];
    	String thirdApplicationId = ApplicationLoader.ApplicationIdList[2];
    	
    	CreateNameRegexRule(firstApplicationId, "\\d", 1.5D);
    	CreateNameRegexRule(secondApplicationId, "\\d", 2D);
    	CreateNameRegexRule(thirdApplicationId, "\\d", 3D);
    	
    	CreateRegexRule(firstApplicationId, "InvalidEmailAddress", "email", "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$", RuleType.RegexNotMatch, 5D);
    	CreateRegexRule(secondApplicationId, "InvalidEmailAddress", "email", "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$", RuleType.RegexNotMatch, 3D);
    	CreateRegexRule(thirdApplicationId, "InvalidEmailAddress", "email", "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$", RuleType.RegexNotMatch, 7D);
    	
    	CreateNameContainsRule(firstApplicationId, "(John)", 5D);
    	CreateNameContainsRule(secondApplicationId, "(Doe)", 3D);
    	
    	CreateInvalidSenderPhoneRule(firstApplicationId, "((?!\\d).)", 5D);
    	CreateInvalidSenderPhoneRule(thirdApplicationId, "((?!\\d).)", 4D);
    	
    	CreateValidReceiverPhoneRule(firstApplicationId, "^(\\d+)*$", -4D);
    	
    	CreateRegexRule(firstApplicationId, "InvalidReceiverPhone", "receiverPhone", "((?!\\d).)", RuleType.RegexMatch, 3D);
    	CreateRegexRule(secondApplicationId, "InvalidReceiverPhone", "receiverPhone", "((?!\\d).)", RuleType.RegexMatch, 3D);
    	CreateRegexRule(thirdApplicationId, "InvalidReceiverPhone", "receiverPhone", "((?!\\d).)", RuleType.RegexMatch, 3D);
    	
    	CreateAmountBetweenRule(firstApplicationId, "100|500", 5D);
    	CreateAmountBetweenRule(secondApplicationId, "200|300", 3D);
    	
    	CreateSqlClauseRule(firstApplicationId, "Select (Count(*) + 1) > 1 From \"transaction\" Where \"order_id\"='#orderId#'", 5D);
    	CreateSqlClauseRule(secondApplicationId, "Select (Count(*) + 1) > 2 From \"transaction\" Where \"order_id\"='#orderId#'", 10.5D);
    }
    
    public void CreateRegexRule(String applicationId, String ruleName, String fieldName, String value, RuleType ruleType, double point)
    {
    	CreateRule(applicationId, value, ruleName, fieldName, ruleType, point);
    }
    
    public void CreateNameRegexRule(String applicationId, String value, double point)
    {
    	CreateRule(applicationId, value, "NameRegex", "nameSurname", RuleType.RegexMatch, point);
    }
    
    public void CreateNameContainsRule(String applicationId, String value, double point)
    {
    	CreateRule(applicationId, value, "NameContains", "nameSurname", RuleType.RegexMatch, point);
    }
    
    public void CreateAmountBetweenRule(String applicationId, String value, double point)
    {
    	CreateRule(applicationId, value, "AmountBetween", "amount", RuleType.AmountBetween, point);
    }
    
    public void CreateInvalidSenderPhoneRule(String applicationId, String value, double point)
    {
    	CreateRule(applicationId, value, "InvalidSenderPhone", "senderPhone", RuleType.RegexMatch, point);
    }
    
    public void CreateValidReceiverPhoneRule(String applicationId, String value, double point)
    {
    	CreateRule(applicationId, value, "ValidReceiverPhone", "receiverPhone", RuleType.RegexMatch, point);
    }
    
    public void CreateSqlClauseRule(String applicationId, String value, double point)
    {
    	CreateRule(applicationId, value, "RetryCount", null, RuleType.SqlClause, point);
    }    
    
    public void CreateRule(String applicationId, String value, String name, String fieldName, RuleType ruleType, double point)
    {
    	ApplicationRule applicationRule = new ApplicationRule();
    	applicationRule.setApplicationId(applicationId);
    	applicationRule.setIsActive(true);
    	applicationRule.setName(name);
    	applicationRule.setFieldName(fieldName);
    	applicationRule.setPoint(point);
    	applicationRule.setValue(value);
    	applicationRule.setRuleType(ruleType);
    	applicationRuleRepository.save(applicationRule);
    	
    	CreateLog(applicationRule);
    }
    
    public void CreateLog(ApplicationRule applicationRule)
    {
    	log.info("Saved Application Rule - id: " + applicationRule.getId());
    }
}