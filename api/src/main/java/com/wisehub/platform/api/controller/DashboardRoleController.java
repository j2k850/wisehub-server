package com.wisehub.platform.api.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wisehub.platform.api.service.CrudService;
import com.wisehub.platform.api.service.DashboardRoleService;
import com.wisehub.platform.data.model.DashboardRole;

import io.swagger.annotations.Api;

@Controller
@RequestMapping("/dashboard_roles")
@Api(value = "API to perform CRUD operation in a Dashboard Role database maintained in apache cassandra", description = "This API provides the capability to perform CRUD operation in a Dashboard Role "
		+ "database maintained in apache cassandra", produces = "application/json")
public class DashboardRoleController extends BaseCrudController<DashboardRole, UUID> {

	private static final Logger logger = LoggerFactory.getLogger(DashboardRoleController.class);

	@Autowired
	private DashboardRoleService service;
	
	@Override
	public CrudService<DashboardRole, UUID> getService() {
		return service;
	}

}