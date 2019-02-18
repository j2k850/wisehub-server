package com.wisehub.platform.data.model.dao.impl;

import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.wisehub.platform.data.model.DashboardUser;
import com.wisehub.platform.data.model.dao.DashboardUserRepository;


@Repository
public class DashboardUserRepositoryImpl extends AbstractVersionCrudRepository<DashboardUser, UUID> implements DashboardUserRepository {

	private static Logger logger = LoggerFactory.getLogger(DashboardUserRepositoryImpl.class.getName());

	private static final String TABLE_NAME = "dashboard_users";

	private static final String KEY_COLUMN = "dashboard_user_id";

	@Override
	public String getTableName() {
		return TABLE_NAME;
	}

	@Override
	public String getKeyName() {
		return KEY_COLUMN;
	}

	@Override
	public Class<DashboardUser> getEntityClass() {
		return DashboardUser.class;
	}
	
	@Override
	public DashboardUser findByEmail(String username) {
		return findByClause(eq("username", username), false);
	}

}