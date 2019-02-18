package com.wisehub.platform.api.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wisehub.platform.api.service.CorrespondenceByUserService;
import com.wisehub.platform.api.service.CrudService;
import com.wisehub.platform.data.model.CorrespondenceByUser;

import io.swagger.annotations.Api;

@Controller
@RequestMapping("/correspondence_by_user")
@Api(value = "API to perform CRUD operation in a AccountByProductStatus database maintained in apache cassandra", description = "This API provides the capability to perform CRUD operation in a AccountByProductStatus "
		+ "database maintained in apache cassandra", produces = "application/json")
public class CorrespondenceByUserController extends BaseCrudController<CorrespondenceByUser, UUID> {

	private static final Logger logger = LoggerFactory.getLogger(CorrespondenceByUserController.class);

	@Autowired
	private CorrespondenceByUserService service;

	@Override
	public CrudService<CorrespondenceByUser, UUID> getService() {
		return service;
	}

}