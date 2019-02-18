package com.wisehub.platform.data.model.dao;

import com.wisehub.platform.data.model.DashboardUserCredential;

public interface DashboardUserCredentialRepository extends CrudRepository<DashboardUserCredential, String> {

	DashboardUserCredential findByEmail(String email);

}