package com.wisehub.platform.core.orm.dao;

import java.util.Collection;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Interval;

import com.wisehub.platform.core.orm.entity.Account;
import com.wisehub.platform.core.orm.entity.Txaction;

public interface TxactionDAO {

	public List<Txaction> findTxactionsInDateRange(Collection<Account> accounts, Interval dateRange);

	public List<Txaction> findTxactionsBeforeDate(Collection<Account> accounts, DateTime endDate);

	public List<Txaction> findTxactionsAfterDate(Collection<Account> accounts, DateTime startDate);

	public List<Txaction> findTxactions(Collection<Account> accounts);

}
