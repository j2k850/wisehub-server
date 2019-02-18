package com.wisehub.platform.api.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wisehub.platform.api.service.CrudService;
import com.wisehub.platform.api.service.ProductService;
import com.wisehub.platform.data.model.Product;

import io.swagger.annotations.Api;

@Controller
@RequestMapping("/products")
@Api(value = "API to perform CRUD operation in a Product database maintained in apache cassandra", description = "This API provides the capability to perform CRUD operation in a Product "
		+ "database maintained in apache cassandra", produces = "application/json")
public class ProductController extends BaseCrudController<Product, UUID> {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService service;

	@Override
	public CrudService<Product, UUID> getService() {
		return service;
	}

}