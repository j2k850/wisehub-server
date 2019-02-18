package com.wisehub.platform.api.sms;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SMSTemplateModeler {
	
	private static final Logger logger = LoggerFactory.getLogger(SMSTemplateModeler.class);

	public static Map<String,Object> model(SendSms vars){
		
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("name", vars.getToName());
		model.put("bank", vars.getBank());
		
		SMSTemplate template = SMSTemplate.getTemplateName(vars.getTemplateId());
		
		switch(template) {
			case RECOMMEND_INSURANCE:
				model.put("insuranceAmount", vars.getInsuranceAmount());
				break;
			case RECOMMEND_CREDITCARD:
				model.put("creditLineAmount", vars.getCreditLineAmount());
				break;
			case RECOMMEND_INVESTMENTS:
				model.put("investmentAmount", vars.getInvestmentAmount());
				model.put("yearlyYield", vars.getYearlyYield());
				model.put("yield", vars.getYield());
				break;
			case RECOMMEND_LOANS:
				model.put("loanAmount",  vars.getLoanAmount());
				break;
			case RECOMMEND_OVERDRAFT:
				model.put("account", vars.getAccount());
				model.put("available", vars.getAvailable());
				break;
			case RECOMMEND_SAVINGS:
				model.put("saveAmount", vars.getSaveAmount());
				model.put("monthlyAmount", vars.getMonthlyAmount());
				model.put("yearlySavings", vars.getYearlySavings());
			case RECOMMEND_AIRTIME:
				model.put("airtimeAmount", vars.getAirtimeAmount());
				break;
			case REMINDER_INSURANCE:
				model.put("due", vars.getDue());
				model.put("account", vars.getAccount());
				model.put("insuranceAmount", vars.getInsuranceAmount());
				break;
			case REMINDER_CREDITCARD:
				model.put("due", vars.getDue());
				model.put("account", vars.getAccount());
				model.put("creditLineAmount", vars.getCreditLineAmount());
				break;
			case REMINDER_INVESTMENTS:
				model.put("due", vars.getDue());
				model.put("account", vars.getAccount());
				model.put("investmentAmount", vars.getInvestmentAmount());
				break;
			case REMINDER_LOANS:
				model.put("due", vars.getDue());
				model.put("account", vars.getAccount());
				model.put("loanAmount",  vars.getLoanAmount());
				break;
			case REMINDER_OVERDRAFT:
				model.put("due", vars.getDue());
				model.put("account", vars.getAccount());
				break;
			case REMINDER_SAVINGS:
				model.put("due", vars.getDue());
				model.put("account", vars.getAccount());
				model.put("saveAmount", vars.getSaveAmount());
				break;		
			default: 
				logger.error("An incorrect template was submiited {}", template.toString());
		}
		
		return model;
	}

}
