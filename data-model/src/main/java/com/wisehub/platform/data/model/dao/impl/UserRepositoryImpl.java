package com.wisehub.platform.data.model.dao.impl;

import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.Clause;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.wisehub.platform.data.model.User;
import com.wisehub.platform.data.model.dao.DTOParameter;
import com.wisehub.platform.data.model.dao.UserRepository;

@Repository
public class UserRepositoryImpl extends AbstractVersionCrudRepository<User, UUID> implements UserRepository {

	private static Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class.getName());

	private static final String TABLE_NAME = "users";

	private static final String KEY_COLUMN = "user_id";

	@Override
	public String getTableName() {
		return TABLE_NAME;
	}

	@Override
	public String getKeyName() {
		return KEY_COLUMN;
	}

	@Override
	public Class<User> getEntityClass() {
		return User.class;
	}
	
	
	public Clause buildWhere(DTOParameter parameter) {
		List<String> names = new ArrayList<>();
		List<Object> values = new ArrayList<>();

		if (parameter.getBvn() != null && !StringUtils.isEmpty(parameter.getBvn().toString())) {
			names.add("bvn");
			values.add(parameter.getBvn());
		}

		return values.isEmpty() ? null : (values.size() > 1 ? eq(names, values) : eq(names.get(0), values.get(0)));
	}

	@Override
	public Long count() {
		Statement  statement = QueryBuilder.select().countAll().from(getKeyspace(), getTableName()).allowFiltering();

		ResultSet executeGenericStatement = this.executeGenericStatement(statement);
		return executeGenericStatement.one().getLong("count");
	}


}