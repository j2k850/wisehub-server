package com.wisehub.platform.api.service.impl;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.twiml.MessagingResponse;
import com.twilio.type.PhoneNumber;
import com.wisehub.platform.api.config.SMSSpringConfig;
import com.wisehub.platform.api.service.SMSService;
import com.wisehub.platform.api.sms.SMSTemplate;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

@Service
public class TwilioSMSServiceImpl implements SMSService {

	private static final Logger logger = LoggerFactory.getLogger(TwilioSMSServiceImpl.class.getName());
	
	@Inject
	private SMSSpringConfig config;

	@Override
	public Message sendSms(UUID id, String fromPhoneNumber,String toPhoneNumber,  String subject, Map<String,Object> model, SMSTemplate template) 
			throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		
		String body = FreeMarkerTemplateUtils.processTemplateIntoString(config.freemarkerConfig().getConfiguration().getTemplate(template.getTemplateName()), model);
		
		logger.debug(body);
	
		return Message
				.creator(new PhoneNumber(toPhoneNumber), 
						new PhoneNumber(fromPhoneNumber), 
						body)
				.create();
	}
	
	@Override
	public MessagingResponse receiveSms(String fromPhoneNumber) {
		return null;
		
	}
}
