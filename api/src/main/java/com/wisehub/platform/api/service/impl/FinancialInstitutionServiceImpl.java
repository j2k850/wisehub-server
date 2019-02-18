package com.wisehub.platform.api.service.impl;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisehub.platform.api.service.FinancialInstitutionService;
import com.wisehub.platform.data.model.FinancialInstitution;
import com.wisehub.platform.data.model.dao.VersionCrudRepository;
import com.wisehub.platform.data.model.dao.FinancialInstitutionRepository;

@Service
public class FinancialInstitutionServiceImpl extends AbstractVersionCrudService<FinancialInstitution> implements FinancialInstitutionService {

	private static Logger logger = LoggerFactory.getLogger(FinancialInstitutionServiceImpl.class.getName());

	@Autowired
	FinancialInstitutionRepository financialInstitutionRepository;

	@Override
	protected VersionCrudRepository<FinancialInstitution, UUID> getRepository() {
		return financialInstitutionRepository;
	}

}
