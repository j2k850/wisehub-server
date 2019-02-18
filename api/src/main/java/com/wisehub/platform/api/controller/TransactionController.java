package com.wisehub.platform.api.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wisehub.platform.api.service.CrudService;
import com.wisehub.platform.api.service.TransactionService;
import com.wisehub.platform.data.model.Transaction;

import io.swagger.annotations.Api;

@Controller
@RequestMapping("/transactions")
@Api(value = "API to perform CRUD operation in a Transaction database maintained in apache cassandra", description = "This API provides the capability to perform CRUD operation in a Transaction "
		+ "database maintained in apache cassandra", produces = "application/json")
public class TransactionController extends BaseCrudController<Transaction, UUID> {

	private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

	@Autowired
	private TransactionService service;

	@Override
	public CrudService<Transaction, UUID> getService() {
		return service;
	}

}