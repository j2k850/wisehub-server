package com.wisehub.platform.data.model.dao.impl;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.wisehub.platform.data.model.Branch;
import com.wisehub.platform.data.model.dao.BranchRepository;

@Repository
public class BranchRepositoryImpl extends AbstractVersionCrudRepository<Branch, UUID> implements BranchRepository {

	private static Logger logger = LoggerFactory.getLogger(BranchRepositoryImpl.class.getName());

	private static final String TABLE_NAME = "branches";

	private static final String KEY_COLUMN = "branch_id";

	@Override
	public String getTableName() {
		return TABLE_NAME;
	}

	@Override
	public String getKeyName() {
		return KEY_COLUMN;
	}

	@Override
	public Class<Branch> getEntityClass() {
		return Branch.class;
	}

}