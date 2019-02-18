package com.wisehub.platform.api.service.impl;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisehub.platform.api.service.CorrespondenceByUserService;
import com.wisehub.platform.data.model.CorrespondenceByUser;
import com.wisehub.platform.data.model.dao.CorrespondenceByUserRepository;
import com.wisehub.platform.data.model.dao.CrudRepository;

@Service
public class CorrespondenceByUserServiceImpl extends AbstractCrudService<CorrespondenceByUser, UUID> implements CorrespondenceByUserService {

	private static Logger logger = LoggerFactory.getLogger(CorrespondenceByUserServiceImpl.class.getName());

	@Autowired
	CorrespondenceByUserRepository repository;

	@Override
	protected CrudRepository<CorrespondenceByUser, UUID> getRepository() {
		return repository;
	}

}
