package com.wisehub.platform.api.email;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum EmailTemplate {
	
	WEEKLY_SUMMARY("1", "email/weekly_summary.ftl"),
	INSUFFICIENT_FUNDS("2","email/insufficient_funds.ftl");
	
	private static final Logger logger = LoggerFactory.getLogger(EmailTemplate.class);
	
	private static final Map<String, EmailTemplate> emailTemplateMap = new HashMap<String,EmailTemplate>();
	
	private static int counter = 1;
	static {
        for (EmailTemplate emailTemplate : values()) {
        		emailTemplateMap.put(String.valueOf(counter++), emailTemplate);
        }
	}
	
	private final String templateId;
	private final String templateName;

	EmailTemplate(String templateId, String templateName) {
		this.templateId = templateId;
		this.templateName = templateName;
	}

	public String getTemplateId() {
		return templateId;
	}

	public String getTemplateName() {
		return templateName;
	}
	
    public static EmailTemplate getTemplateName(String key) {
        return emailTemplateMap.get(key);
    }
}
