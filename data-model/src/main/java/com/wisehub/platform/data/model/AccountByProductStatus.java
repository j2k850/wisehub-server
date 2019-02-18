package com.wisehub.platform.data.model;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import com.datastax.driver.mapping.annotations.ClusteringColumn;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.FrozenValue;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
@Table(name = "accounts_by_productstatus", keyspace = "platform")
public class AccountByProductStatus {

	@ApiModelProperty(readOnly = true, required=false, name = "account_id")
	@PartitionKey
	@Column(name = "account_id")
	private UUID accountId;

	@ApiModelProperty(readOnly = true, required=false, name = "created_at")
	@ClusteringColumn(value = 0)
	@Column(name = "created_at")
	private Date createdAt;

	@ClusteringColumn(value = 1)
	@Column(name = "product_id")
	@ApiModelProperty(readOnly = true, required=false, name = "product_id")
	private UUID productId;

	@ApiModelProperty(dataType = "Map")
	@FrozenValue
	@Column(name = "product_status")
	private Map<String, UDTProductStatus> productStatus;

	public UUID getAccountId() {
		return accountId;
	}

	public void setAccountId(UUID accountId) {
		this.accountId = accountId;
	}

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

	public Map<String, UDTProductStatus> getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(Map<String, UDTProductStatus> productStatus) {
		this.productStatus = productStatus;
	}

}