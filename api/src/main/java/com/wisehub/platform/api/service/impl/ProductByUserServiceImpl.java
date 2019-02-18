package com.wisehub.platform.api.service.impl;

import java.util.Date;
import java.util.UUID;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisehub.platform.api.service.ProductByUserService;
import com.wisehub.platform.data.model.ProductByUser;
import com.wisehub.platform.data.model.UDTProductStatus;
import com.wisehub.platform.data.model.dao.CrudRepository;
import com.wisehub.platform.data.model.dao.ProductByUserRepository;

@Service
public class ProductByUserServiceImpl extends AbstractCrudService<ProductByUser, UUID> implements ProductByUserService {

	private static Logger logger = LoggerFactory.getLogger(ProductByUserServiceImpl.class.getName());

	@Autowired
	ProductByUserRepository repository;

	@Override
	protected CrudRepository<ProductByUser, UUID> getRepository() {
		return repository;
	}
	
	@Override
	protected void beforePost(ProductByUser model) {
		model.setCreatedAt(new Date());
		
		if (model.getProductStatus() != null || model.getProductStatus().isEmpty()){
			for (Entry<String, UDTProductStatus> productStatus : model.getProductStatus().entrySet()) {
				productStatus.getValue().setCreated_at(new Date());
			}
		}
	}

}
