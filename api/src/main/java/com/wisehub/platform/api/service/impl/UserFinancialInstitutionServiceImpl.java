package com.wisehub.platform.api.service.impl;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisehub.platform.api.service.UserFinancialInstitutionService;
import com.wisehub.platform.data.model.UserFinancialInstitution;
import com.wisehub.platform.data.model.dao.CrudRepository;
import com.wisehub.platform.data.model.dao.UserFinancialInstitutionRepository;

@Service
public class UserFinancialInstitutionServiceImpl extends AbstractCrudService<UserFinancialInstitution, UUID> implements UserFinancialInstitutionService {

	private static Logger logger = LoggerFactory.getLogger(UserFinancialInstitutionServiceImpl.class.getName());

	@Autowired
	UserFinancialInstitutionRepository repository;

	@Override
	protected CrudRepository<UserFinancialInstitution, UUID> getRepository() {
		return repository;
	}

}
