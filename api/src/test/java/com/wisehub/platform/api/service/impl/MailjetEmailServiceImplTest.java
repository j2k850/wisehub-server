package com.wisehub.platform.api.service.impl;

import static org.mockito.Mockito.mock;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.twilio.rest.api.v2010.account.Message;
import com.wisehub.platform.api.email.EmailTemplate;

public class MailjetEmailServiceImplTest {
	
	private static final Logger logger  = LoggerFactory.getLogger(MailjetEmailServiceImplTest.class.getName());
	
	private Message mockMessage;
	private Map<String,Object> model;
	
	private MailJetEmailServiceImpl emailService;
	
	private String recps = "j2k850@gmail.com, jkwofie@wisehub.io";
	
	@Before
    public void setUp() {
		emailService = new MailJetEmailServiceImpl();
		mockMessage = mock(Message.class);
		model = new HashMap<String,Object>();
		model.put("name","Barbara Iyayi");
		model.put("available", "1000 Naira");
		model.put("account", "123456789");
		
		
    }
	
	//@Test
	public void validate() {
		 try {
			  String message = emailService.sendEmail(UUID.randomUUID(), "WiseHub", "info@wisehub.io", "Wisehub Overdraft Notification (Test)",model, EmailTemplate.WEEKLY_SUMMARY,recps,"");
			  
		 }catch (Throwable e) {
			 logger.error(e.getMessage());
		 }
	}

}
