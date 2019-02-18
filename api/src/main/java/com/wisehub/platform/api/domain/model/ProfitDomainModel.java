package com.wisehub.platform.api.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;

public class ProfitDomainModel {

	private UUID id;
	private BigDecimal profit;
	private Date date;

	public ProfitDomainModel() {
	}

	@JsonCreator
	public ProfitDomainModel(UUID id, BigDecimal profit, Date date) {
		super();
		this.id = id;
		this.profit = profit;
		this.date = date;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getId() {
		return id;
	}

	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
