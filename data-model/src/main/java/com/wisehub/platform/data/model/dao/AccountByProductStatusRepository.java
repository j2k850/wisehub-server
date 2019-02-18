package com.wisehub.platform.data.model.dao;

import java.util.UUID;

import com.wisehub.platform.data.model.AccountByProductStatus;

public interface AccountByProductStatusRepository extends CrudRepository<AccountByProductStatus, UUID> {
	
	public Long acountByStatus(DTOParameter parameter, String status);

}