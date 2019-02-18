package com.wisehub.platform.api.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisehub.platform.api.service.UserCredentialService;
import com.wisehub.platform.data.model.UserCredential;
import com.wisehub.platform.data.model.dao.CrudRepository;
import com.wisehub.platform.data.model.dao.UserCredentialRepository;

@Service
public class UserCredentialServiceImpl extends AbstractCrudService<UserCredential, String> implements UserCredentialService {

	private static Logger logger = LoggerFactory.getLogger(UserCredentialServiceImpl.class.getName());

	@Autowired
	UserCredentialRepository userCredentialRepository;
	
	@Override
	protected CrudRepository<UserCredential, String> getRepository() {
		return userCredentialRepository;
	}
	

}
