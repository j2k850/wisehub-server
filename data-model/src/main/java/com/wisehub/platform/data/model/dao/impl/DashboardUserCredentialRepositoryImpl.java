package com.wisehub.platform.data.model.dao.impl;

import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.wisehub.platform.data.model.DashboardUserCredential;
import com.wisehub.platform.data.model.dao.DashboardUserCredentialRepository;

@Repository
public class DashboardUserCredentialRepositoryImpl extends AbstractCrudRepository<DashboardUserCredential, String>
		implements DashboardUserCredentialRepository {

	private static Logger logger = LoggerFactory.getLogger(DashboardUserCredentialRepositoryImpl.class.getName());

	private static final String TABLE_NAME = "dashboard_user_credentials";

	private static final String KEY_COLUMN = "email";

	@Override
	public String getTableName() {
		return TABLE_NAME;
	}

	@Override
	public String getKeyName() {
		return KEY_COLUMN;
	}

	@Override
	public Class<DashboardUserCredential> getEntityClass() {
		return DashboardUserCredential.class;
	}

	@Override
	public DashboardUserCredential findByEmail(String email) {
		return findByClause(eq("email", email), false);
	}

}