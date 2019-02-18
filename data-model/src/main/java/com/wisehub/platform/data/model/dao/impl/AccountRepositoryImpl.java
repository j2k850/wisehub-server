package com.wisehub.platform.data.model.dao.impl;

import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.datastax.driver.core.LocalDate;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.Clause;
import com.datastax.driver.core.querybuilder.Ordering;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.wisehub.platform.data.model.Account;
import com.wisehub.platform.data.model.AccountStatus;
import com.wisehub.platform.data.model.dao.AccountRepository;
import com.wisehub.platform.data.model.dao.DTOParameter;

@Repository
public class AccountRepositoryImpl extends AbstractVersionCrudRepository<Account, UUID> implements AccountRepository {

	private static Logger logger = LoggerFactory.getLogger(AccountRepositoryImpl.class.getName());

	private static final String TABLE_NAME = "accounts";

	private static final String KEY_COLUMN = "account_id";

	@Override
	public String getTableName() {
		return TABLE_NAME;
	}

	@Override
	public String getKeyName() {
		return KEY_COLUMN;
	}

	@Override
	public Class<Account> getEntityClass() {
		return Account.class;
	}

	@Override
	public void save(Account model) {
		if (model.getStatus() == null) {
			model.setStatus(AccountStatus.ACTIVE);
		}

		super.save(model);
	}

	public Clause buildWhere(DTOParameter parameter) {
		List<String> names = new ArrayList<>();
		List<Object> values = new ArrayList<>();

		// if (parameter.getUserId() != null && !StringUtils.isEmpty(parameter.getUserId().toString())) {
		// names.add("user_id");
		// values.add(UUID.fromString(parameter.getUserId()));
		// }

		if (parameter.getAccountNumber() != null && !StringUtils.isEmpty(parameter.getAccountNumber().toString())) {
			names.add("account_number");
			values.add(Long.valueOf(parameter.getAccountNumber()));
		}

		return values.isEmpty() ? null : (values.size() > 1 ? eq(names, values) : eq(names.get(0), values.get(0)));
	}

	@Override
	public Long acountByStatus(DTOParameter parameter, AccountStatus status) {
		List<String> names = new ArrayList<>();
		List<Object> values = new ArrayList<>();

		if (parameter.getAccountId() != null && !StringUtils.isEmpty(parameter.getAccountId().toString())) {
			names.add(KEY_COLUMN);
			values.add(parameter.getAccountId());

			if (parameter.getCreatedAt() != null && !StringUtils.isEmpty(parameter.getCreatedAt().toString())) {
				names.add("created_at");
				values.add(parameter.getCreatedAt());

				if (parameter.getUserId() != null && !StringUtils.isEmpty(parameter.getUserId().toString())) {
					names.add("user_id");
					values.add(parameter.getUserId());
				}
			}
		}

		if (status != null) {
			names.add("account_status");
			values.add(status.toString());
		}

		Clause clauseWhere = values.isEmpty() ? null : (values.size() > 1 ? eq(names, values) : eq(names.get(0), values.get(0)));

		Statement statement;
		if (clauseWhere != null) {
			statement = QueryBuilder.select().countAll().from(getKeyspace(), getTableName()).allowFiltering().where(clauseWhere);
		} else {
			statement = QueryBuilder.select().countAll().from(getKeyspace(), getTableName()).allowFiltering();
		}

		ResultSet executeGenericStatement = this.executeGenericStatement(statement);
		return executeGenericStatement.one().getLong("count");
	}

	@Override
	public Date findFirstSale() {
		Statement statement;
		statement = QueryBuilder.select("created_at").from(getKeyspace(), getTableName()).allowFiltering()
				.orderBy(QueryBuilder.asc("created_at")).limit(1);

		ResultSet executeGenericStatement = this.executeGenericStatement(statement);
		LocalDate localDate = executeGenericStatement.one().getDate("created_at");
		
		Date date = new Date(localDate.getYear(), localDate.getMonth(), localDate.getDay());

		return date;
	}

	@Override
	public Date findLastSale() {
		Statement statement;
		statement = QueryBuilder.select("created_at").from(getKeyspace(), getTableName()).allowFiltering()
				.orderBy(QueryBuilder.desc("created_at")).limit(1);

		ResultSet executeGenericStatement = this.executeGenericStatement(statement);
		LocalDate localDate = executeGenericStatement.one().getDate("created_at");
		
		Date date = new Date(localDate.getYear(), localDate.getMonth(), localDate.getDay());

		return date;
	}

}