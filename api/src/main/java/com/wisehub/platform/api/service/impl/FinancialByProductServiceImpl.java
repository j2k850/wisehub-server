package com.wisehub.platform.api.service.impl;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisehub.platform.api.service.FinancialByProductService;
import com.wisehub.platform.data.model.FinancialByProduct;
import com.wisehub.platform.data.model.dao.FinancialByProductRepository;
import com.wisehub.platform.data.model.dao.CrudRepository;

@Service
public class FinancialByProductServiceImpl extends AbstractCrudService<FinancialByProduct, UUID> implements FinancialByProductService {

	private static Logger logger = LoggerFactory.getLogger(FinancialByProductServiceImpl.class.getName());

	@Autowired
	FinancialByProductRepository repository;

	@Override
	protected CrudRepository<FinancialByProduct, UUID> getRepository() {
		return repository;
	}

}
