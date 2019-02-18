package com.wisehub.platform.data.model.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wisehub.platform.data.model.VersionPM;
import com.wisehub.platform.data.model.dao.CrudRepository;
import com.wisehub.platform.data.model.dao.VersionCrudRepository;

public abstract class AbstractVersionCrudRepository<M extends VersionPM, ID> extends AbstractCrudRepository<M, ID>
		implements CrudRepository<M, ID>, VersionCrudRepository<M, ID> {
	
	private static Logger logger = LoggerFactory.getLogger(AbstractVersionCrudRepository.class.getName());

}