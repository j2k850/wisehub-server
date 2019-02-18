package com.wisehub.platform.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wisehub.platform.api.service.CrudService;
import com.wisehub.platform.api.service.WeeklyExpensesByUserService;
import com.wisehub.platform.data.model.WeeklyExpensesByUser;

import io.swagger.annotations.Api;

@Controller
@RequestMapping("/weeklyExpensesByUser")
@Api(value = "API to perform CRUD operation in a WeeklyExpensesByUser database maintained in apache cassandra", description = "This API provides the capability to perform CRUD operation in a WeeklyExpensesByUser "
		+ "database maintained in apache cassandra", produces = "application/json")
public class WeeklyExpensesByUserController extends BaseCrudController<WeeklyExpensesByUser, String> {

	private static final Logger logger = LoggerFactory.getLogger(WeeklyExpensesByUserController.class);

	@Autowired
	private WeeklyExpensesByUserService service;

	@Override
	public CrudService<WeeklyExpensesByUser, String> getService() {
		return service;
	}

}