package com.wisehub.platform.data.model.dao;

import java.util.List;
import java.util.UUID;

import com.wisehub.platform.data.model.DashboardRole;
import com.wisehub.platform.data.model.DashboardUserRole;

public interface DashboardRoleRepository extends VersionCrudRepository<DashboardRole, UUID> {

	List<DashboardRole> findsByRolesId(List<DashboardUserRole> userRoles);
	
	DashboardRole findByRoleName(String roleName);

}