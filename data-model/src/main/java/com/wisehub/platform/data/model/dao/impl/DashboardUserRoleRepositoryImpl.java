package com.wisehub.platform.data.model.dao.impl;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.wisehub.platform.data.model.DashboardUserRole;
import com.wisehub.platform.data.model.dao.DashboardUserRoleRepository;

@Repository
public class DashboardUserRoleRepositoryImpl extends AbstractCrudRepository<DashboardUserRole, UUID> implements DashboardUserRoleRepository {

	private static Logger logger = LoggerFactory.getLogger(DashboardUserRoleRepositoryImpl.class.getName());

	private static final String TABLE_NAME = "dashboard_user_roles";

	private static final String KEY_COLUMN = "dashboard_user_id ";

	@Override
	public String getTableName() {
		return TABLE_NAME;
	}

	@Override
	public String getKeyName() {
		return KEY_COLUMN;
	}

	@Override
	public Class<DashboardUserRole> getEntityClass() {
		return DashboardUserRole.class;
	}

}