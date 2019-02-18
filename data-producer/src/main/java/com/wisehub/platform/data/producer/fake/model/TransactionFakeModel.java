package com.wisehub.platform.data.producer.fake.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransactionFakeModel {

	private static final Logger LOG = LoggerFactory.getLogger(TransactionFakeModel.class);

	private TransactionType transactionType;
	private CategoriesType categoriesType;

	private String description;
	private Date date;
	private BigDecimal deposits;
	private BigDecimal withdrawals;
	private BigDecimal balance;

	public TransactionFakeModel() {
	}

	public TransactionFakeModel(UUID userId, UUID accountId, long bnv, long accountNum, TransactionType transactionType, CategoriesType categoriesType,
			Date date) {
		this.transactionType = transactionType;
		this.categoriesType = categoriesType;
		this.date = date;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public CategoriesType getCategoriesType() {
		return categoriesType;
	}

	public void setCategoriesType(CategoriesType categoriesType) {
		this.categoriesType = categoriesType;
	}

	public BigDecimal getDeposits() {
		return deposits;
	}

	public void setDeposits(BigDecimal deposits) {
		this.deposits = deposits;
	}

	public BigDecimal getWithdrawals() {
		return withdrawals;
	}

	public void setWithdrawals(BigDecimal withdrawals) {
		this.withdrawals = withdrawals;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}