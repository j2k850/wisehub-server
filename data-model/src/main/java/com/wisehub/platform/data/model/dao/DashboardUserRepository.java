package com.wisehub.platform.data.model.dao;

import java.util.UUID;

import com.wisehub.platform.data.model.DashboardUser;

public interface DashboardUserRepository extends VersionCrudRepository<DashboardUser, UUID> {

	DashboardUser findByEmail(String username);

}