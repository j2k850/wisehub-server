package com.wisehub.platform.api.view.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountInfoViewModel {

	@JsonProperty("bank_name")
	String bankName;
	@JsonProperty("product_name")
	String productName;
	@JsonProperty("status")
	String status;

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
