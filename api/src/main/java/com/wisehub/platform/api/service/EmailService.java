package com.wisehub.platform.api.service;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import com.wisehub.platform.api.email.EmailTemplate;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

public interface EmailService {

	String sendEmail(UUID id, String fromName, String fromEmail,String subject, Map<String,Object> model, EmailTemplate template,
			String recps, String recpName) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException;
	
}
