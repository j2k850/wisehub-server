package com.wisehub.platform.api.service.impl;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisehub.platform.api.service.DashboardRoleService;
import com.wisehub.platform.data.model.DashboardRole;
import com.wisehub.platform.data.model.dao.DashboardRoleRepository;
import com.wisehub.platform.data.model.dao.VersionCrudRepository;

@Service
public class DashboardRoleServiceImpl extends AbstractVersionCrudService<DashboardRole> implements DashboardRoleService {

	private static Logger logger = LoggerFactory.getLogger(DashboardRoleServiceImpl.class.getName());

	@Autowired
	DashboardRoleRepository dashboardRoleRepository;

	@Override
	protected VersionCrudRepository<DashboardRole, UUID> getRepository() {
		return dashboardRoleRepository;
	}

}
