package com.wisehub.platform.api.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.twiml.MessagingResponse;
import com.wisehub.platform.api.config.SMSSpringConfig;
import com.wisehub.platform.api.service.impl.TwilioSMSServiceImpl;
import com.wisehub.platform.api.sms.SMSTemplate;
import com.wisehub.platform.api.sms.SMSTemplateModeler;
import com.wisehub.platform.api.sms.SendSms;

import io.swagger.annotations.Api;

@Controller
@Api(value = "API to consume sms messages", description = "API to consume sms messages", produces = "application/json")
public class SMSController {

	private static final Logger logger = LoggerFactory.getLogger(SMSController.class);
	
	@Autowired
	private TwilioSMSServiceImpl smsService;
	
	//@Autowired
	private SMSSpringConfig smsConfig = new SMSSpringConfig(); // why not injecting

	@RequestMapping(value = {"/receiveSms"} , method = {RequestMethod.GET, RequestMethod.POST}, produces = ("text/xml"))
	public void  receiveSms(@RequestParam(value = "From") final String fromPhoneNumber,
			 	 @RequestBody SendSms vars,
				 HttpServletRequest request,
				 HttpServletResponse response) throws Exception {

		      MessagingResponse twiml = smsService.receiveSms(fromPhoneNumber);
		   
		      response.setContentType("application/xml");
		      response.getWriter().print(twiml.toXml());
			
		}
	
	@RequestMapping(value = {"/sendSms"} , method = {RequestMethod.GET, RequestMethod.POST})
	public void  sendSms(@RequestBody SendSms vars,
					 HttpServletRequest request,
					 HttpServletResponse response) throws Exception {
		
			Map<String, Object> model = SMSTemplateModeler.model(vars);
			
			String fromNumber = smsConfig.defaultFromPhoneNumber();
		
			logger.info("fromNumber= {}, toNumber= {}, subject = {}, templateId = {}, model = {}", fromNumber, vars.getToPhoneNumber(),
				vars.getSubject(), vars.getTemplateId(), model);
			
			smsConfig.initTwilioSMSProd();

		    Message message = smsService.sendSms(vars.getId(), fromNumber, vars.getToPhoneNumber(), vars.getSubject(), model, 
		    		  SMSTemplate.getTemplateName(vars.getTemplateId()));
		      
		     logger.info(message.getSid());
			
	}	
}