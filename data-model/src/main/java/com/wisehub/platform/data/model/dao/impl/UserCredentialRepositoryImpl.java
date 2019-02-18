package com.wisehub.platform.data.model.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.wisehub.platform.data.model.UserCredential;
import com.wisehub.platform.data.model.dao.UserCredentialRepository;

@Repository
public class UserCredentialRepositoryImpl extends AbstractCrudRepository<UserCredential, String> implements UserCredentialRepository {

	private static Logger logger = LoggerFactory.getLogger(UserCredentialRepositoryImpl.class.getName());

	private static final String TABLE_NAME = "user_credentials";

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
	public Class<UserCredential> getEntityClass() {
		return UserCredential.class;
	}

}