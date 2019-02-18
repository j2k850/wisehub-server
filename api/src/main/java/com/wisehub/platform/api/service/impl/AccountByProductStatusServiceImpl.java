package com.wisehub.platform.api.service.impl;

import java.util.Date;
import java.util.Map.Entry;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisehub.platform.api.service.AccountByProductStatusService;
import com.wisehub.platform.data.model.AccountByProductStatus;
import com.wisehub.platform.data.model.AccountStatus;
import com.wisehub.platform.data.model.UDTProductStatus;
import com.wisehub.platform.data.model.dao.AccountByProductStatusRepository;
import com.wisehub.platform.data.model.dao.CrudRepository;

@Service
public class AccountByProductStatusServiceImpl extends AbstractCrudService<AccountByProductStatus, UUID> implements AccountByProductStatusService {

	private static Logger logger = LoggerFactory.getLogger(AccountByProductStatusServiceImpl.class.getName());

	@Autowired
	AccountByProductStatusRepository accountByProductStatusRepository;

	@Override
	protected CrudRepository<AccountByProductStatus, UUID> getRepository() {
		return accountByProductStatusRepository;
	}

	@Override
	protected void beforePost(AccountByProductStatus model) {
		model.setCreatedAt(new Date());
		
		if (model.getProductStatus() != null || model.getProductStatus().isEmpty()){
			for (Entry<String, UDTProductStatus> productStatus : model.getProductStatus().entrySet()) {
				productStatus.getValue().setCreated_at(new Date());
			}
		}
			
	}

}
