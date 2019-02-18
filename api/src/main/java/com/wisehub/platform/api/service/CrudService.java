package com.wisehub.platform.api.service;

import java.util.List;

import com.wisehub.platform.data.model.dao.DTOParameter;

public interface CrudService<T, ID> {

	public List<T> getAll(DTOParameter parameter);

	public T get(ID id) throws Exception;

	public void post(T model);

	public void put(ID id, T model) throws Exception;

	public void delete(ID id) throws Exception;

}
