package com.wisehub.platform.api.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wisehub.platform.api.service.CrudService;
import com.wisehub.platform.api.service.FinancialInstitutionService;
import com.wisehub.platform.data.model.FinancialInstitution;

import io.swagger.annotations.Api;

@Controller
@RequestMapping("/financialInstitutions")
@Api(value = "API to perform CRUD operation in a Financial Institution database maintained in apache cassandra", description = "This API provides the capability to perform CRUD operation in a FinancialInstitution "
		+ "database maintained in apache cassandra", produces = "application/json")
public class FinancialInstitutionController extends BaseCrudController<FinancialInstitution, UUID> {

	private static final Logger logger = LoggerFactory.getLogger(FinancialInstitutionController.class);

	@Autowired
	private FinancialInstitutionService service;

	@Override
	public CrudService<FinancialInstitution, UUID> getService() {
		return service;
	}

}