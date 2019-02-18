package com.wisehub.platform.api.view.model;

import java.math.BigDecimal;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class CustomerCLVViewModel  {

	private String month;

	@JsonProperty("customer_full_name")
	private String customer;

	@JsonProperty("customer_id")
	private UUID customerId;

	private BigDecimal amount;

	private BigDecimal total;

	private BigDecimal average;


	public CustomerCLVViewModel() {
	}
	
	public CustomerCLVViewModel(String key, BigDecimal average) {
		this.month = key;
		this.average = average;
	}
	

	public CustomerCLVViewModel(UUID id, String customer, BigDecimal amount) {
		this.customerId = id;
		this.customer = customer;
		this.amount = amount;

	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public UUID getCustomerId() {
		return customerId;
	}

	public void setCustomerId(UUID customerId) {
		this.customerId = customerId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getAverage() {
		return average;
	}

	public void setAverage(BigDecimal average) {
		this.average = average;
	}


}
