package com.wisehub.platform.api.sms;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum SMSTemplate {
	
	RECOMMEND_INSURANCE("1", "sms/recommend_insurance.ftl"), 
	RECOMMEND_CREDITCARD("2","sms/recommend_creditcard.ftl"),
	RECOMMEND_INVESTMENTS("3","sms/recommend_investments.ftl"),
	RECOMMEND_OVERDRAFT("4","sms/recommend_overdraft.ftl"),
	RECOMMEND_LOANS("5","sms/recommend_loans.ftl"),
	RECOMMEND_SAVINGS("6","sms/recommend_savings.ftl"),
	REMINDER_INSURANCE("7", "sms/reminder_insurance.ftl"), 
	REMINDER_CREDITCARD("8","sms/reminder_creditcard.ftl"),
	REMINDER_INVESTMENTS("9","sms/reminder_investments.ftl"),
	REMINDER_OVERDRAFT("10","sms/reminder_overdraft.ftl"),
	REMINDER_LOANS("11","sms/reminder_loans.ftl"),
	REMINDER_SAVINGS("12","sms/reminder_savings.ftl"),
	RECOMMEND_AIRTIME("13","sms/recommend_airtime.ftl");

	private static final Logger logger = LoggerFactory.getLogger(SMSTemplate.class);
	
	private static final Map<String, SMSTemplate> smsTemplateMap = new HashMap<String,SMSTemplate>();
	
	private static int counter = 1;
	static {
        for (SMSTemplate smsTemplate : values()) {
        	smsTemplateMap.put(String.valueOf(counter++), smsTemplate);
        }
	}

	private final String templateId;
	private final String templateName;

	SMSTemplate(String templateId, String templateName) {
		this.templateId = templateId;
		this.templateName = templateName;
	}

	public String getTemplateId() {
		return templateId;
	}

	public String getTemplateName() {
		return templateName;
	}

    public static SMSTemplate getTemplateName(String key) {
        return smsTemplateMap.get(key);
    }
}
