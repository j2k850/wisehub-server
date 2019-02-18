package com.wisehub.platform.data.model.dao.impl;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.wisehub.platform.data.model.FinancialInstitution;
import com.wisehub.platform.data.model.dao.FinancialInstitutionRepository;

@Repository
public class FinancialInstitutionRepositoryImpl extends AbstractVersionCrudRepository<FinancialInstitution, UUID> implements FinancialInstitutionRepository {

	private static Logger logger = LoggerFactory.getLogger(FinancialInstitutionRepositoryImpl.class.getName());

	private static final String TABLE_NAME = "financial_institutions";

	private static final String KEY_COLUMN = "fin_inst_id";

	@Override
	public String getTableName() {
		return TABLE_NAME;
	}

	@Override
	public String getKeyName() {
		return KEY_COLUMN;
	}

	@Override
	public Class<FinancialInstitution> getEntityClass() {
		return FinancialInstitution.class;
	}

}