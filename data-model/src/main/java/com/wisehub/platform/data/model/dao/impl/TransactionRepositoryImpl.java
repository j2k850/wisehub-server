package com.wisehub.platform.data.model.dao.impl;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.mapping.Result;
import com.wisehub.platform.data.model.Transaction;
import com.wisehub.platform.data.model.dao.TransactionRepository;

@Repository
public class TransactionRepositoryImpl extends AbstractVersionCrudRepository<Transaction, UUID> implements TransactionRepository {

	private static Logger logger = LoggerFactory.getLogger(TransactionRepositoryImpl.class.getName());

	private static final String TABLE_NAME = "transactions";

	private static final String KEY_COLUMN = "tx_id";

	@Override
	public String getTableName() {
		return TABLE_NAME;
	}

	@Override
	public String getKeyName() {
		return KEY_COLUMN;
	}

	@Override
	public Class<Transaction> getEntityClass() {
		return Transaction.class;
	}

	/**
	 * 
	 */
	@Override
	public Transaction findLast() {
		// FIXME: https://stackoverflow.com/questions/46921455/cassandra-error-order-by-only-supported-when-partition-key-is-restricted-by-eq
		// Order By only supported when partition key is restricted by EQ or IN
//		Statement statement;
//		statement = QueryBuilder.select().from(getKeyspace(), getTableName()).allowFiltering()
//				.orderBy(QueryBuilder.desc("created_at")).limit(1);
		
		Statement statement;
		statement = QueryBuilder.select().from(getKeyspace(), getTableName()).allowFiltering().limit(1);

		Result<Transaction> executeStatement = this.executeStatement(statement);
		executeStatement.one();
		return executeStatement.one();
	}

}