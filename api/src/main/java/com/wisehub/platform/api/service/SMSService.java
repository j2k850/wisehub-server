package com.wisehub.platform.api.service;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.twiml.MessagingResponse;
import com.wisehub.platform.api.sms.SMSTemplate;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

public interface SMSService {
	
	Message sendSms(UUID id, String toPhoneNumber, String fromPhoneNumber, String subject, Map<String,Object> model, SMSTemplate template) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException;
	
	MessagingResponse receiveSms(String fromPhoneNumber); 
	
}
