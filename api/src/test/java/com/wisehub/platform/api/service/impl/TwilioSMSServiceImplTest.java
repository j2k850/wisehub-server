package com.wisehub.platform.api.service.impl;

import static org.mockito.Mockito.mock;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.twilio.rest.api.v2010.account.Message;
import com.wisehub.platform.api.sms.SMSTemplate;

public class TwilioSMSServiceImplTest {
	
	private static final Logger logger  = LoggerFactory.getLogger(TwilioSMSServiceImplTest.class.getName());
	
	private Message mockMessage;
	private Map<String,Object> model;
	
	private TwilioSMSServiceImpl twilioService;
	
	@Before
    public void setUp() {
		twilioService = new TwilioSMSServiceImpl();
		mockMessage = mock(Message.class);
		model = new HashMap<String,Object>();
		model.put("name","Barbara Iyayi");
		model.put("available", "1000 Naira");
		model.put("account", "123456789");
    }
	
	//@Test
	public void validate() {
		
		 try {
			  Message message = twilioService.sendSms(UUID.randomUUID(), "+447492886416", "+19738860779", "Test Subject",model, SMSTemplate.RECOMMEND_OVERDRAFT);
		 }catch (Throwable e) {
			 logger.error(e.getMessage());
		 }
	}

}
