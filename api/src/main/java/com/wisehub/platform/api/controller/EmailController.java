package com.wisehub.platform.api.controller;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisehub.platform.api.email.EmailTemplate;
import com.wisehub.platform.api.email.SendEmail;
import com.wisehub.platform.api.service.impl.MailJetEmailServiceImpl;
import com.wisehub.platform.api.service.impl.WeeklyExpensesByUserServiceImpl;
import com.wisehub.platform.api.service.impl.WeeklyIncomeByUserServiceImpl;
import com.wisehub.platform.api.view.model.ExpensesViewModel;
import com.wisehub.platform.api.view.model.RevenueViewModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@Api(value = "API to send out email messages", description = "API to send email messages", produces = "application/json")
public class EmailController {

	private static final Logger  logger = LoggerFactory.getLogger(EmailController.class);
	
	@Autowired
	private MailJetEmailServiceImpl emailService;
	
	@Autowired
	private WeeklyIncomeByUserServiceImpl weeklyIncomeByUser;
	
	@Autowired
	private WeeklyExpensesByUserServiceImpl weeklyExpensesByUser;

	@ApiOperation(value = "Send an email to customer by Id", produces = "application/json")
	@RequestMapping(value = {"/sendEmail"}, method = {RequestMethod.POST})
	public @ResponseBody String  sendEmail(
			 @RequestBody SendEmail vars,
			 HttpServletRequest request,
			 HttpServletResponse response) throws Exception {
		
		
		RevenueViewModel userWeeklyEarnings = weeklyIncomeByUser.getRevenue(vars.getId());
		ExpensesViewModel userWeeklyExpenses = weeklyExpensesByUser.getExpenses(vars.getId());
		
		Map<String, Object> model = whichModel(vars, userWeeklyEarnings, userWeeklyExpenses);
		
		logger.info("fromName= {}, fromEmail= {}, subject = {}, recps = {}, templateId = {}, model = {}", vars.getFromName(), vars.getFromEmail(),
				vars.getSubject(), vars.getRecps(), vars.getTemplateId(), model);
		
		return emailService.sendEmail(vars.getId(), vars.getFromName(), vars.getFromEmail(), vars.getSubject(), model, 
				EmailTemplate.getTemplateName(vars.getTemplateId()), vars.getRecps(), vars.getRecpName());	
	}

	private Map<String,Object> whichModel(SendEmail vars, RevenueViewModel userWeeklyEarnings, ExpensesViewModel userWeeklyExpenses){
		
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("name", vars.getRecpName());
		model.put("bank", vars.getBank());
		
		EmailTemplate template = EmailTemplate.getTemplateName(vars.getTemplateId());
	
		switch(template) {
			case WEEKLY_SUMMARY:
				model.put("title","It’s summary time. How’s your spending?");
				model.put("earnings", userWeeklyEarnings);
				model.put("expenses", userWeeklyExpenses);
				model.put("cashItems", userWeeklyEarnings.getCashModels());
				model.put("investmentItems", userWeeklyEarnings.getInvestmentModels());
				model.put("loanItems", userWeeklyExpenses.getLoanViewModels());
				break;
			case INSUFFICIENT_FUNDS:
				model.put("title","An important notice about insufficient funds in your account");
				model.put("account", "tbd");
				break;
			default: 
				logger.error("An incorrect template was submiited {}", template.toString());
		}
		
		return model;
	}
}