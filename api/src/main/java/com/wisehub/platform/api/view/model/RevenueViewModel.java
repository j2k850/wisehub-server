package com.wisehub.platform.api.view.model;

import java.math.BigDecimal;
import java.util.List;

public class RevenueViewModel {

	private BigDecimal cashTotal;
	private BigDecimal investmentsTotal;
	private String currency;
	private List<CashViewModel> cashModels;
	private List<InvestmentViewModel> investmentModels;

	public RevenueViewModel() {
	}

	public RevenueViewModel(BigDecimal cashTotal, String currency) {
		super();
		this.cashTotal = cashTotal;
		this.currency = currency;
	}

  public BigDecimal getCashTotal() {
		return cashTotal;
  }
	
  public void setCashTotal(BigDecimal cashTotal) {
		this.cashTotal = cashTotal;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String symbol) {
		this.currency = symbol;
	}

	public List<CashViewModel> getCashModels() {
		return cashModels;
	}

	public void setCashModels(List<CashViewModel> cashModels) {
		this.cashModels = cashModels;
	}
	
	public List<InvestmentViewModel> getInvestmentModels() {
		return investmentModels;
	}

	public void setInvestmentModels(List<InvestmentViewModel> investmentModels) {
		this.investmentModels = investmentModels;
	}

	public BigDecimal getInvestmentsTotal() {
		return investmentsTotal;
	}

	public void setInvestmentsTotal(BigDecimal investmentsTotal) {
		this.investmentsTotal = investmentsTotal;
	}
}
