package com.wisehub.platform.api.view.model;

import java.util.Date;

public class SaleAccountEventViewModel {

	private Date date;
	private String event;
	private String customer;
	private String message;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String product) {
		this.message = product;
	}

}
