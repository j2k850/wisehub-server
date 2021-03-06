package com.wisehub.platform.api.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wisehub.platform.api.service.AccountService;
import com.wisehub.platform.api.service.CrudService;
import com.wisehub.platform.data.model.Account;

import io.swagger.annotations.Api;

@Controller
@RequestMapping("/accounts")
@Api(value = "API to perform CRUD operation in a Account database maintained in apache cassandra", description = "This API provides the capability to perform CRUD operation in a Account "
		+ "database maintained in apache cassandra", produces = "application/json")
public class AccountController extends BaseCrudController<Account, UUID> {

	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private AccountService service;

	@Override
	public CrudService<Account, UUID> getService() {
		return service;
	}

}