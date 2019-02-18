package com.wisehub.platform.api.service.impl;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisehub.platform.api.service.DashboardUserService;
import com.wisehub.platform.data.model.DashboardUser;
import com.wisehub.platform.data.model.dao.DashboardUserRepository;
import com.wisehub.platform.data.model.dao.VersionCrudRepository;

@Service
public class DashboardUserServiceImpl extends AbstractVersionCrudService<DashboardUser> implements DashboardUserService {

	private static Logger logger = LoggerFactory.getLogger(DashboardUserServiceImpl.class.getName());

	@Autowired
	DashboardUserRepository dashboardUserRepository;

	@Override
	protected VersionCrudRepository<DashboardUser, UUID> getRepository() {
		return dashboardUserRepository;
	}

}
