package com.wisehub.platform.data.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import com.datastax.driver.mapping.annotations.ClusteringColumn;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
@Table(name = "financials_by_product", keyspace = "platform")
public class FinancialByProduct {

	@ApiModelProperty(name = "product_id")
	@PartitionKey
	@Column(name = "product_id")
	private UUID productId;

	@ApiModelProperty(value = "The database generated user ID", name = "created_at")
	@Column(name = "created_at")
	@ClusteringColumn(value = 0)
	private Date createdAt;

	@ApiModelProperty(value = "The database generated account ID", name = "payments_received")
	@Column(name = "payments_received")
	private BigDecimal paymentsReceived;

	@ApiModelProperty(value = "The database generated account ID", name = "fee_income_received")
	@Column(name = "fee_income_received")
	private BigDecimal feeIncomeReceived;

	@ApiModelProperty(value = "The database generated account ID", name = "interest_income_received")
	@Column(name = "interest_income_received")
	private BigDecimal interestIncomeReceived;

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createAt) {
		this.createdAt = createAt;
	}

	public UUID getProductId() {
		return productId;
	}

	public void setProductId(UUID productId) {
		this.productId = productId;
	}

	public BigDecimal getPaymentsReceived() {
		return paymentsReceived;
	}

	public void setPaymentsReceived(BigDecimal paymentsReceived) {
		this.paymentsReceived = paymentsReceived;
	}

	public BigDecimal getFeeIncomeReceived() {
		return feeIncomeReceived;
	}

	public void setFeeIncomeReceived(BigDecimal feeIncomeReceived) {
		this.feeIncomeReceived = feeIncomeReceived;
	}

	public BigDecimal getInterestIncomeReceived() {
		return interestIncomeReceived;
	}

	public void setInterestIncomeReceived(BigDecimal interestIncomeReceived) {
		this.interestIncomeReceived = interestIncomeReceived;
	}

}