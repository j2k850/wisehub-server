package com.wisehub.platform.api.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wisehub.platform.api.service.CrudService;
import com.wisehub.platform.data.model.dao.CrudRepository;
import com.wisehub.platform.data.model.dao.DTOParameter;

public abstract class AbstractCrudService<M, ID> implements CrudService<M, ID> {

	private static Logger logger = LoggerFactory.getLogger(AbstractCrudService.class.getName());

	protected abstract CrudRepository<M, ID> getRepository();

	public M get(ID id) throws Exception {
		logger.info("in get");
		M userM = getRepository().findById(id);
		if (userM == null) {
			throw new Exception("Failed to get M for id :" + id.toString());
		}
		return userM;
	}

	public void post(M model) {
		logger.debug("in post");
		beforePost(model);
		getRepository().save(model);
		afterPost(model);
	}

	protected void afterPost(M model) {
	}

	protected void beforePost(M model) {
	}

	public void put(ID id, M model) throws Exception {
		logger.debug("in put");

		M m = getRepository().findById(id);
		if (m == null) {
			throw new Exception("Failed to get M for id :" + id.toString());
		}
		beforePut(model, m);

		getRepository().save(model);
		afterPut(model);

	}

	protected void beforePut(M model, M m) {
	}

	protected void afterPut(M model) {
	}

	public void delete(ID id) throws Exception {
		logger.debug(" in delete ");

		M userM = getRepository().findById(id);
		if (userM == null) {
			throw new Exception("Failed to get M for id :" + id.toString());
		}

		getRepository().delete(id);
	}

	public List<M> getAll(DTOParameter parameter) {
		logger.debug("get all");
		return getRepository().findAll(parameter);
	}

}
