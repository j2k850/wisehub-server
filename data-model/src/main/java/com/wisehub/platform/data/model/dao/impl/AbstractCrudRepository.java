package com.wisehub.platform.data.model.dao.impl;

import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;

import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.Clause;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.datastax.driver.mapping.Result;
import com.wisehub.platform.data.model.CassandraConnector;
import com.wisehub.platform.data.model.dao.CrudRepository;
import com.wisehub.platform.data.model.dao.DTOParameter;

public abstract class AbstractCrudRepository<M, ID> implements CrudRepository<M, ID> {

	private static Logger logger = LoggerFactory.getLogger(AbstractCrudRepository.class.getName());

	protected Session session;
	protected MappingManager manager;
	protected Mapper<M> mapper;

	@Autowired
	private CassandraConnector cassandraConnector;

	@Value("${cassandra.keyspace.name}")
	private String keyspaceName;
	

	protected String getKeyspace(){
		return keyspaceName;
	}
	
	
	@PostConstruct
	public void init() {
		try {
			session = cassandraConnector.getSession();
			manager = new MappingManager(session);
			mapper = manager.mapper(this.getEntityClass());
		} catch (Exception e) {
			throw new RuntimeException("Failed to initiate MService", e);
		}
	}

	public abstract Class<M> getEntityClass();

	public abstract String getTableName();

	public abstract String getKeyName();

	/**
	 * Save .
	 * 
	 * @param model
	 */
	@Override
	public void save(M model) {
		logger.error("Creating {} with name :: {}", this.getEntityClass().getSimpleName(), model.toString());
		mapper.save(model);
	}

	public Clause buildWhere(DTOParameter parameter) {
		return null;
	}
	
	/**
	 * Find all models from models
	 * 
	 * @return List<M>
	 */
	@Override
	public List<M> findAll(DTOParameter parameter) {
		Result<M> result = null;
		
		Clause clauseWhere = parameter != null ? buildWhere(parameter) : null;
		Statement statement;
		if (clauseWhere != null){
			statement = QueryBuilder.select().from(keyspaceName, getTableName()).allowFiltering().where(clauseWhere);
		}else{
			statement = QueryBuilder.select().from(keyspaceName, getTableName());
		}
		
		statement.setFetchSize(10);
		
		statement.setConsistencyLevel(cassandraConnector.getConsistencyLevel());
		try {
			ResultSet resultSet = session.execute(statement);
			result = mapper.map(resultSet);

		} catch (Exception e) {
			logger.debug("Error",e);
		}

		return result == null ? null : result.all();
	}


	protected Result<M> executeStatement(Statement statement) {
		Result<M> result = null;
		statement.setConsistencyLevel(cassandraConnector.getConsistencyLevel());
		try {
			ResultSet resultSet = session.execute(statement);
			result = mapper.map(resultSet);

		} catch (Exception e) {
			logger.debug("Error",e);
		}

		return result;
	}
	
	
	protected ResultSet executeGenericStatement(Statement statement) {
		statement.setConsistencyLevel(cassandraConnector.getConsistencyLevel());
		try {
			ResultSet resultSet = session.execute(statement);
			return resultSet;
		} catch (Exception e) {
			logger.debug("Error",e);
		}

		return null;
	}
	
	/**
	 * Find all models from models 
	 * @param allowFiltering 
	 * 
	 * @return List<M>
	 */
	public List<M> findsByClause(Clause clauseWhere, boolean allowFiltering) {
		Result<M> result = null;
		result = find(clauseWhere, allowFiltering, result);

		return result == null ? null : result.all();
	}
	
	
	/**
	 * Find all models from models 
	 * @param allowFiltering 
	 * 
	 * @return List<M>
	 */
	public M findByClause(Clause clauseWhere, boolean allowFiltering) {
		Result<M> result = null;
		result = find(clauseWhere, allowFiltering, result);

		return result == null ? null : result.one();
	}

	/**
	 * 
	 * @param clauseWhere
	 * @param allowFiltering
	 * @param result
	 * @return
	 */
	private Result<M> find(Clause clauseWhere, boolean allowFiltering, Result<M> result) {
		Statement statement;
		if (clauseWhere != null && allowFiltering){
			statement = QueryBuilder.select().from(keyspaceName, getTableName()).allowFiltering().where(clauseWhere);
		}else if (allowFiltering){
			statement = QueryBuilder.select().from(keyspaceName, getTableName()).allowFiltering();
		}else if (clauseWhere != null){
			statement = QueryBuilder.select().from(keyspaceName, getTableName()).where(clauseWhere);
		}else{
			statement = QueryBuilder.select().from(keyspaceName, getTableName());
		}
		
		statement.setConsistencyLevel(cassandraConnector.getConsistencyLevel());
		try {
			ResultSet resultSet = session.execute(statement);
			result = mapper.map(resultSet);

		} catch (Exception e) {
			logger.debug("Error",e);
		}
		return result;
	}
	
	
	/**
	 * 
	 */
	@Override
	public M findById(ID uuid) {
		Result<M> result = null;
		Statement statement = QueryBuilder.select().from(keyspaceName, getTableName()).where(eq(getKeyName(), uuid)).setFetchSize(10);
		statement.setConsistencyLevel(cassandraConnector.getConsistencyLevel());
		try {
			ResultSet resultSet = session.execute(statement);
			result = mapper.map(resultSet);

		} catch (Exception e) {
			logger.debug("Error",e);
		}

		return result == null ? null : result.one();
	}

	/**
	 * Delete a user by id.
	 */
	@Override
	public void delete(ID id) throws Exception {
		logger.error("Trying to remove {} with id :: {}", this.getEntityClass().getSimpleName(), id);

		M model = findById(id);

		if (model == null) {
			throw new Exception("Failed to delete " + this.getEntityClass().getSimpleName() + " for id :" + id.toString());
		}
		mapper.delete(model);

	}

}