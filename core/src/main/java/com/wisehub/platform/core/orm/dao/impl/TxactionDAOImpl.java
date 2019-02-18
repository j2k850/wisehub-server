package com.wisehub.platform.core.orm.dao.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.wisehub.platform.core.orm.dao.TxactionDAO;
import com.wisehub.platform.core.orm.entity.Account;
import com.wisehub.platform.core.orm.entity.Txaction;


/**
 * A data access object for retrieving and storing {@link Txaction} instances.
 * 
 * @author coda
 * @see Txaction
 */
public class TxactionDAOImpl implements TxactionDAO {
	
	private static Logger logger = LoggerFactory.getLogger(TxactionDAOImpl.class.getName());

	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Finds all {@link Txaction} instances which belong to a set of
	 * {@link Account}s and which were posted in a given {@link Interval}.
	 * 
	 * @param accounts
	 *            a set of Accounts
	 * @param dateRange
	 *            the interval of time
	 * @return a list of transactions in reverse chronological order
	 */
	@SuppressWarnings("unchecked")
	public List<Txaction> findTxactionsInDateRange(Collection<Account> accounts, Interval dateRange) {
		if (accounts.isEmpty()) {
			return Collections.emptyList();
		}
		
		Session session = this.sessionFactory.openSession();

		return session.getNamedQuery("com.wisehub.platform.core.orm.entity.Txaction.findInDateRange")
					.setParameterList("accounts", accounts)
					.setParameter("startDate", dateRange.getStart())
				.setParameter("endDate", dateRange.getEnd()).list();
		
	}

	/**
	 * Finds all {@link Txaction} instances which belong to a set of
	 * {@link Account}s and which were posted before a given {@link DateTime}.
	 * 
	 * @param accounts
	 *            a set of Accounts
	 * @param endDate
	 *            the date after which transactions should be ignored
	 * @return a list of transactions in reverse chronological order
	 */
	@SuppressWarnings("unchecked")
	public List<Txaction> findTxactionsBeforeDate(Collection<Account> accounts, DateTime endDate) {
		if (accounts.isEmpty()) {
			return Collections.emptyList();
		}
		
		Session session = this.sessionFactory.openSession();

		return (List<Txaction>) session.getNamedQuery("com.wisehub.platform.core.orm.entity.Txaction.findBeforeDate")
					.setParameterList("accounts", accounts)
				.setParameter("endDate", endDate);
	}

	/**
	 * Finds all {@link Txaction} instances which belong to a set of
	 * {@link Account}s and which were posted after a given {@link DateTime}.
	 * 
	 * @param accounts
	 *            a set of Accounts
	 * @param startDate
	 *            the date before which transactions should be ignored
	 * @return a list of transactions in reverse chronological order
	 */
	@SuppressWarnings("unchecked")
	public List<Txaction> findTxactionsAfterDate(Collection<Account> accounts, DateTime startDate) {
		if (accounts.isEmpty()) {
			return Collections.emptyList();
		}
		
		Session session = this.sessionFactory.openSession();

		return (List<Txaction>) session.getNamedQuery("com.wisehub.platform.core.orm.entity.Txaction.findAfterDate")
					.setParameterList("accounts", accounts)
					.setParameter("startDate", startDate)
		;
	}

	/**
	 * Finds all {@link Txaction} instances which belong to a set of
	 * {@link Account}s.
	 * 
	 * @param accounts
	 *            a set of Accounts
	 * @return a list of transactions in reverse chronological order
	 */
	@SuppressWarnings("unchecked")
	public List<Txaction> findTxactions(Collection<Account> accounts) {
		if (accounts.isEmpty()) {
			return Collections.emptyList();
		}
		
		Session session = this.sessionFactory.openSession();

		return (List<Txaction>) session.getNamedQuery("com.wisehub.platform.core.orm.entity.Txaction.findInAccounts")
				.setParameterList("accounts", accounts);
	}
}