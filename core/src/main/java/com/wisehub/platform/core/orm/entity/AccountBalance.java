package com.wisehub.platform.core.orm.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import com.wisehub.platform.core.money.CurrencyMismatchException;
//import com.wisehub.platform.core.money.Money;

@Entity
@Table(name = "account_balance")
@NamedQueries({
		@NamedQuery(name = "com.wisehub.platform.core.orm.entity.AccountBalance.findByAccountKeyAndRelativeAccountIdAndBalanceId", query = "SELECT b FROM Account a, AccountBalance b"
				+ " WHERE a.accountKey = :accountKey AND a = b.account AND b.account_balance_id = :accountBalanceId") })
public class AccountBalance {

	private static Logger logger = LoggerFactory.getLogger(AccountBalance.class.getName());

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "account_balance_id", nullable = false)
	private Integer account_balance_id;

	@Column(nullable = false, name = "init_balance")
	private BigDecimal initBalance;

	@Column(nullable = false, name = "curr_balance")
	private BigDecimal currBalance;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "account_id")
	private Account account;

	@Column(name = "balance_date")
	private DateTime date;

	@Column(name = "created")
	private DateTime created;

	@Column(name = "last_mod")
	private DateTime lastMod;

	public AccountBalance() {
		// nothing here yet
	}

	public AccountBalance(Account account, BigDecimal currBalance, DateTime date) {
		this.account = account;
		this.currBalance = currBalance;
		this.date = date;
	}

	public AccountBalance(Account account, BigDecimal initBalance, BigDecimal currBalance, DateTime date) {
		this.account = account;
		this.initBalance = initBalance;
		this.currBalance = currBalance;
		this.date = date;
	}

	/*
	 * 
	 * public AccountBalance(Account account, Money balance, DateTime date) {
	 * this(account, balance.getValue(), date);
	 * 
	 * if (!account.getCurrency().equals(balance.getCurrency())) { throw new
	 * CurrencyMismatchException("create an AccountBalance with",
	 * account.getCurrency(), balance.getCurrency()); } }
	 */

	public int getId() {
		return account_balance_id;
	}

	/*
	 * public Money getBalance() { return new Money(currBalance,
	 * account.getCurrency()); }
	 */

	public Account getAccount() {
		return account;
	}

	public DateTime getDate() {
		return date;
	}

	public DateTime getCreated() {
		return created;
	}

	public void setCreated(DateTime created) {
		this.created = created;
	}

	public DateTime getLastMod() {
		return lastMod;
	}

	public void setLastMod(DateTime lastMod) {
		this.lastMod = lastMod;
	}
}
