package com.wisehub.platform.api.view.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountByProductViewModel {

	@JsonProperty(value = "accounts_opened")
	private Long accountsOpened;

	@JsonProperty(value = "accounts_pending")
	private Long accountsPending;

	@JsonProperty(value = "accounts")
	private Long accounts;

	@JsonProperty(value = "payment_received")
	private BigDecimal paymentReceived;

	@JsonProperty(value = "transaction_categories")
	private List<TransactionCategoryViewModel> transactionCategories;

	@JsonProperty(value = "overdraft_customers_over_time")
	private List<CustomerOverTimeViewModel> overdraftCustomersOverTime;

	@JsonProperty(value = "inactive_customers_over_time")
	private List<CustomerOverTimeViewModel> inactiveCustomersOverTime;

	public AccountByProductViewModel() {
	}

	public Long getAccountsOpened() {
		return accountsOpened;
	}

	public void setAccountsOpened(Long accountsOpened) {
		this.accountsOpened = accountsOpened;
	}

	public Long getAccountsPending() {
		return accountsPending;
	}

	public void setAccountsPending(Long accountsPending) {
		this.accountsPending = accountsPending;
	}

	public Long getAccounts() {
		return accounts;
	}

	public void setAccounts(Long accounts) {
		this.accounts = accounts;
	}

	public BigDecimal getPaymentReceived() {
		return paymentReceived;
	}

	public void setPaymentReceived(BigDecimal paymentReceived) {
		this.paymentReceived = paymentReceived;
	}

	public List<TransactionCategoryViewModel> getTransactionCategories() {
		return transactionCategories;
	}

	public void setTransactionCategories(List<TransactionCategoryViewModel> transactionCategories) {
		this.transactionCategories = transactionCategories;
	}

	public List<CustomerOverTimeViewModel> getOverdraftCustomersOverTime() {
		return overdraftCustomersOverTime;
	}

	public void setOverdraftCustomersOverTime(List<CustomerOverTimeViewModel> overdraftCustomersOverTime) {
		this.overdraftCustomersOverTime = overdraftCustomersOverTime;
	}

	public List<CustomerOverTimeViewModel> getInactiveCustomersOverTime() {
		return inactiveCustomersOverTime;
	}

	public void setInactiveCustomersOverTime(List<CustomerOverTimeViewModel> inactiveCustomersOverTime) {
		this.inactiveCustomersOverTime = inactiveCustomersOverTime;
	}

}
