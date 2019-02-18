package com.wisehub.platform.data.model.dao.impl;

import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.Clause;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.mapping.Result;
import com.wisehub.platform.data.model.AccountByProductStatus;
import com.wisehub.platform.data.model.ProductStatus;
import com.wisehub.platform.data.model.dao.AccountByProductStatusRepository;
import com.wisehub.platform.data.model.dao.AccountRepository;
import com.wisehub.platform.data.model.dao.DTOParameter;

@Repository
public class AccountByProductStatusRepositoryImpl extends AbstractCrudRepository<AccountByProductStatus, UUID> implements AccountByProductStatusRepository {

	private static Logger logger = LoggerFactory.getLogger(AccountByProductStatusRepositoryImpl.class.getName());

	private static final String TABLE_NAME = "accounts_by_productstatus";

	private static final String KEY_COLUMN = "account_id";

	@Autowired
	AccountRepository accountRepository; 
	
	@Override
	public String getTableName() {
		return TABLE_NAME;
	}

	@Override
	public String getKeyName() {
		return KEY_COLUMN;
	}

	@Override
	public Class<AccountByProductStatus> getEntityClass() {
		return AccountByProductStatus.class;
	}

	public Clause buildWhere(DTOParameter parameter) {
		List<String> names = new ArrayList<>();
		List<Object> values = new ArrayList<>();

		if (parameter.getAccountId() != null && !StringUtils.isEmpty(parameter.getAccountId().toString())) {
			names.add(KEY_COLUMN);
			values.add(parameter.getAccountId());

			if (parameter.getCreatedAt() != null && !StringUtils.isEmpty(parameter.getCreatedAt().toString())) {
				names.add("created_at");
				values.add(parameter.getCreatedAt());

				if (parameter.getProductId() != null && !StringUtils.isEmpty(parameter.getProductId().toString())) {
					names.add("product_id");
					values.add(parameter.getProductId());
				}
			}

		}

		return values.isEmpty() ? null : (values.size() > 1 ? eq(names, values) : eq(names.get(0), values.get(0)));
	}

	@Override
	public Long acountByStatus(DTOParameter parameter, String acountByStatus) {
		ProductStatus status = ProductStatus.byName(acountByStatus.toUpperCase());
		
		List<String> names = new ArrayList<>();
		List<Object> values = new ArrayList<>();

		if (parameter.getAccountId() != null && !StringUtils.isEmpty(parameter.getAccountId().toString())) {
			names.add(KEY_COLUMN);
			values.add(parameter.getAccountId());

			if (parameter.getCreatedAt() != null && !StringUtils.isEmpty(parameter.getCreatedAt().toString())) {
				names.add("created_at");
				values.add(parameter.getCreatedAt());

				if (parameter.getProductId() != null && !StringUtils.isEmpty(parameter.getProductId().toString())) {
					names.add("product_id");
					values.add(parameter.getProductId());
				}
			}
		}
		
		names.add("product_status.status");
		values.add(status.getDescription());
		
		Clause clauseWhere = values.isEmpty() ? null : (values.size() > 1 ? eq(names, values) : eq(names.get(0), values.get(0)));

		Statement statement;
		if (clauseWhere != null){
			statement = QueryBuilder.select().countAll().from(getKeyspace(),getTableName()).where(clauseWhere);
		}else{
			statement = QueryBuilder.select().countAll().from(getKeyspace(),getTableName());
		}
		
		ResultSet executeGenericStatement = this.executeGenericStatement(statement);
		return executeGenericStatement.one().getLong("count");
	}
	

}