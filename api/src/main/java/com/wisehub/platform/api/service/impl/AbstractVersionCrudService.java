package com.wisehub.platform.api.service.impl;

import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wisehub.platform.api.service.VersionCrudService;
import com.wisehub.platform.api.service.CrudService;
import com.wisehub.platform.data.model.VersionPM;
import com.wisehub.platform.data.model.dao.VersionCrudRepository;

public abstract class AbstractVersionCrudService<M extends VersionPM> extends AbstractCrudService<M,UUID> implements CrudService<M,UUID>,VersionCrudService<M,UUID> {

	private static Logger logger = LoggerFactory.getLogger(AbstractVersionCrudService.class.getName());

	protected abstract VersionCrudRepository<M, UUID> getRepository();

	public M get(UUID id) throws Exception {
		logger.info("in get");
		M userM = getRepository().findById(id);
		if (userM == null) {
			throw new Exception("Failed to get M for id :" + id.toString());
		}
		return userM;
	}

	public void post(M model) {
		logger.debug("in post");
		model.setId(UUID.randomUUID());
		Date createdAt = new Date();
		model.setCreatedAt(createdAt);
		model.setUpdatedAt(createdAt);
		super.post(model);
	}

	public void put(UUID id, M model) throws Exception {
		logger.debug("in put");
		Date updatedAt = new Date();
		model.setUpdatedAt(updatedAt);
		super.put(id,model);
	}

}
