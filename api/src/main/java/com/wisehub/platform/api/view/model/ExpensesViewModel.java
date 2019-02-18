package com.wisehub.platform.api.view.model;

import java.math.BigDecimal;
import java.util.List;

public class ExpensesViewModel {

	private BigDecimal totalSpent;
	private BigDecimal loansTotal;
	private String currency;
	private List<LoanViewModel> loanViewModels;

	public BigDecimal getTotalSpent() {
		return totalSpent;
	}

	public void setTotalSpent(BigDecimal totalSpent) {
		this.totalSpent = totalSpent;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String symbol) {
		this.currency = symbol;
	}

	public List<LoanViewModel> getLoanViewModels() {
		return loanViewModels;
	}

	public void setLoanViewModels(List<LoanViewModel> loanViewModels) {
		this.loanViewModels = loanViewModels;
	}

	public BigDecimal getLoansTotal() {
		return loansTotal;
	}

	public void setLoansTotal(BigDecimal loansTotal) {
		this.loansTotal = loansTotal;
	}

}
