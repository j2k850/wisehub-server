package com.wisehub.platform.data.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
@Table(name = "transactions", keyspace = "platform")
public class Transaction implements VersionPM {

	@ApiModelProperty(name = "tx_id")
	@PartitionKey
	@Column(name = "tx_id")
	private UUID id;

	@ApiModelProperty(name = "account_id")
	@Column(name = "account_id")
	private UUID accountId;

	@ApiModelProperty(name = "user_id")
	@Column(name = "user_id")
	private UUID userId;

	@ApiModelProperty(name = "description")
	@Column(name = "description")
	private String description;

	@ApiModelProperty(name = "deposits")
	@Column(name = "deposits")
	private BigDecimal deposits;

	@ApiModelProperty(name = "withdrawals")
	@Column(name = "withdrawals")
	private BigDecimal withdrawals;

	@ApiModelProperty(name = "balance")
	@Column(name = "balance")
	private BigDecimal balance;

	@ApiModelProperty(name = "date_posted")
	@Column(name = "date_posted")
	private LocalDate datePosted;

	@ApiModelProperty(name = "created_at")
	@Column(name = "created_at")
	private Date createdAt;

	@ApiModelProperty(name = "updated_at")
	@Column(name = "updated_at")
	private Date updatedAt;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getAccountId() {
		return accountId;
	}

	public void setAccountId(UUID accountId) {
		this.accountId = accountId;
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getDeposits() {
		return deposits;
	}

	public void setDeposits(BigDecimal deposits) {
		this.deposits = deposits;
	}

	public BigDecimal getWithdrawals() {
		return withdrawals;
	}

	public void setWithdrawals(BigDecimal withdrawals) {
		this.withdrawals = withdrawals;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public LocalDate getDatePosted() {
		return datePosted;
	}

	public void setDatePosted(LocalDate datePosted) {
		this.datePosted = datePosted;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}