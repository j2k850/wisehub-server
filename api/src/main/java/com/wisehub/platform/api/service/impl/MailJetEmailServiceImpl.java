package com.wisehub.platform.api.service.impl;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.mailjet.client.resource.Emailv31;
import com.wisehub.platform.api.config.EmailSpringConfig;
import com.wisehub.platform.api.email.EmailTemplate;
import com.wisehub.platform.api.service.EmailService;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

@Service
public class MailJetEmailServiceImpl implements EmailService {

	private static final Logger logger = LoggerFactory.getLogger(MailJetEmailServiceImpl.class.getName());
	
	@Inject
	EmailSpringConfig config = new EmailSpringConfig(); // why not injecting
	
	@Autowired
	CorrespondenceByUserServiceImpl correspondenceByUserServiceImpl;

	private final MailjetClient mailJetClient = config.getMailJetEmailProd();

	public MailJetEmailServiceImpl() {}

	@Override
	public String sendEmail(UUID id, String fromName, String fromEmail, String subject, Map<String,Object> model, EmailTemplate template,
			String recps, String recpName) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		
		String body = FreeMarkerTemplateUtils.processTemplateIntoString(config.freemarkerConfig().getConfiguration().getTemplate(template.getTemplateName()), model);
		
		return send(fromEmail, fromName, subject, body,recps, recpName);
	}

	private String send(String fromEmail, String fromName,String subject, String body,String recps, String recpName) {
		
		MailjetResponse response = null;
		
		try {
			
			MailjetRequest email = new MailjetRequest(Emailv31.resource)
		            .property(Emailv31.MESSAGES, new JSONArray()
			                .put(new JSONObject()
			                    .put(Emailv31.Message.FROM, new JSONObject()
			                        .put("Email",fromEmail)
			                        .put("Name", fromName))
			                    .put(Emailv31.Message.TO, new JSONArray()
			                        .put(new JSONObject()
			                            .put("Email",recps)
			                            .put("Name", recpName)))
			                    .put(Emailv31.Message.SUBJECT, subject)
			                    .put(Emailv31.Message.HTMLPART, body)));
			
			 logger.info(email.toString());
			 
			 response = mailJetClient.post(email);
			 
			if (response.getStatus() != 200) {
				logger.error("Failed to send Email via Mailjet StatusCode:{} Message:{}", response.getStatus(),
						response.getData());
				
				//here is where we log event
				//correspondenceByUserServiceImpl.saveEvent(id,<>)
			} else {
				logger.info("Sent email via Mailjet StatusCode:{} Message:{}", response.getStatus(),
						response.getData());
				
				//here is where we log event
				//correspondenceByUserServiceImpl.saveEvent(id,<>)
			}
		} catch (MailjetException me)  {
			logger.error("Failed to send Mailjet Email", me);
		} catch (MailjetSocketTimeoutException me) {
			logger.error("Mailjet Email Socket Timeout", me);
		}
		return String.valueOf(response.getStatus());
	}
}
