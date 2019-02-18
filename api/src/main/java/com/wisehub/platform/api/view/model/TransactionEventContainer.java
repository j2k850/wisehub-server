package com.wisehub.platform.api.view.model;

public class TransactionEventContainer {

	private Iterable<TransactionEventViewModel> transactions;

	public Iterable<TransactionEventViewModel> getTransactions() {
		return transactions;
	}

	public void setTransactions(Iterable<TransactionEventViewModel> transactions) {
		this.transactions = transactions;
	}

}