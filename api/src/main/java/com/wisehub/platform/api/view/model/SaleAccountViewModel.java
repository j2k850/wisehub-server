package com.wisehub.platform.api.view.model;

import java.math.BigDecimal;
import java.util.UUID;

import java.util.Date;

public class SaleAccountViewModel {

	private UUID id;
	private BigDecimal price;
	private Date date;
	private String currency;

	
	public SaleAccountViewModel() {
	}

	
	
	public SaleAccountViewModel(UUID id, BigDecimal price, Date date, String currency) {
		super();
		this.id = id;
		this.price = price;
		this.date = date;
		this.currency = currency;
	}



	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
