package com.wisehub.platform.api.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wisehub.platform.api.service.CrudService;
import com.wisehub.platform.api.service.UserFinancialInstitutionService;
import com.wisehub.platform.data.model.UserFinancialInstitution;

import io.swagger.annotations.Api;

@Controller
@RequestMapping("/userFinancialInstitution")
@Api(value = "API to perform CRUD operation in a UserFinancialInstitution database maintained in apache cassandra", description = "This API provides the capability to perform CRUD operation in a UserFinancialInstitution "
		+ "database maintained in apache cassandra", produces = "application/json")
public class UserFinancialInstitutionController extends BaseCrudController<UserFinancialInstitution, UUID> {

	private static final Logger logger = LoggerFactory.getLogger(UserFinancialInstitutionController.class);

	@Autowired
	private UserFinancialInstitutionService service;

	@Override
	public CrudService<UserFinancialInstitution, UUID> getService() {
		return service;
	}

}