package com.wisehub.platform.data.model.dao.impl;

import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;
import static com.datastax.driver.core.querybuilder.QueryBuilder.in;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.wisehub.platform.data.model.DashboardRole;
import com.wisehub.platform.data.model.DashboardUserRole;
import com.wisehub.platform.data.model.dao.DashboardRoleRepository;

@Repository
public class DashboardRoleRepositoryImpl extends AbstractVersionCrudRepository<DashboardRole, UUID> implements DashboardRoleRepository {

	private static Logger logger = LoggerFactory.getLogger(DashboardRoleRepositoryImpl.class.getName());

	private static final String TABLE_NAME = "dashboard_roles";

	private static final String KEY_COLUMN = "dashboard_role_id";

	@Override
	public String getTableName() {
		return TABLE_NAME;
	}

	@Override
	public String getKeyName() {
		return KEY_COLUMN;
	}

	@Override
	public Class<DashboardRole> getEntityClass() {
		return DashboardRole.class;
	}

	@Override
	public List<DashboardRole> findsByRolesId(List<DashboardUserRole> userRoles) {
		List<UUID> rolesId = userRoles.stream().map(u -> u.getDashboardRoleId()).collect(Collectors.toList());

		return findsByClause(in(KEY_COLUMN, rolesId), false);

	}

	@Override
	public DashboardRole findByRoleName(String roleName) {
		return findByClause(eq("dashboard_name", roleName), true);
	}

}