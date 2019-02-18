package com.wisehub.platform.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wisehub.platform.api.service.CrudService;
import com.wisehub.platform.api.service.WeeklyIncomeByUserService;
import com.wisehub.platform.data.model.WeeklyIncomeByUser;

import io.swagger.annotations.Api;

@Controller
@RequestMapping("/weeklyIncomeByUser")
@Api(value = "API to perform CRUD operation in a WeeklyIncomeByUser database maintained in apache cassandra", description = "This API provides the capability to perform CRUD operation in a WeeklyIncomeByUser "
		+ "database maintained in apache cassandra", produces = "application/json")
public class WeeklyIncomeByUserController extends BaseCrudController<WeeklyIncomeByUser, String> {

	private static final Logger logger = LoggerFactory.getLogger(WeeklyIncomeByUserController.class);

	@Autowired
	private WeeklyIncomeByUserService service;

	@Override
	public CrudService<WeeklyIncomeByUser, String> getService() {
		return service;
	}

}