package com.wisehub.platform.data.model.dao;

import java.util.List;

public interface CrudRepository<M, ID> {

	M findById(ID uuid);

	void save(M model);

	List<M> findAll(DTOParameter parameter);

	void delete(ID id) throws Exception;

}
