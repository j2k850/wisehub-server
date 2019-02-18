package com.wisehub.platform.data.model;

import java.math.BigDecimal;

import com.datastax.driver.mapping.annotations.Field;
import com.datastax.driver.mapping.annotations.UDT;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "tx_category_amount")
@UDT(name = "tx_category_amount", keyspace = "platform")
public class UDTTxCategoryAmount {

	@ApiModelProperty(value = "The type of account", dataType = "String")
	@Field(name = "account_type")
	private AccountType accountType;

	@ApiModelProperty(value = "The category of transaction", dataType = "String")
	@Field(name = "tx_category")
	private String category;

	@ApiModelProperty(value = "The amount of transaction ", dataType = "decimal")
	@Field(name = "amount")
	private BigDecimal amount;

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
