package com.wisehub.platform.api.service.impl;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisehub.platform.api.service.UserService;
import com.wisehub.platform.data.model.User;
import com.wisehub.platform.data.model.dao.VersionCrudRepository;
import com.wisehub.platform.data.model.dao.UserRepository;

@Service
public class UserServiceImpl extends AbstractVersionCrudService<User> implements UserService {

	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class.getName());

	@Autowired
	UserRepository userRepository;

	@Override
	protected VersionCrudRepository<User, UUID> getRepository() {
		return userRepository;
	}

	@Override
	public Long count() {
		return userRepository.count();
	}

}
