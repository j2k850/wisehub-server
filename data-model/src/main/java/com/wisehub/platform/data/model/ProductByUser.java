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
@Table(name = "products_by_user", keyspace = "platform")
public class ProductByUser {

	@ApiModelProperty(name = "product_id")
	@PartitionKey
	@Column(name = "product_id")
	private UUID productId;

	@ApiModelProperty(name = "user_id")
	@ClusteringColumn(value = 0)
	@Column(name = "user_id")
	private UUID userId;

	@ApiModelProperty(name = "created_at")
	@ClusteringColumn(value = 1)
	@Column(name = "created_at")
	private Date createdAt;

	@ApiModelProperty(name = "product_status")
	@FrozenValue
	@Column(name = "product_status")
	private Map<String, UDTProductStatus> productStatus;

	public UUID getProductId() {
		return productId;
	}

	public void setProductId(UUID productId) {
		this.productId = productId;
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createAt) {
		this.createdAt = createAt;
	}

	public Map<String, UDTProductStatus> getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(Map<String, UDTProductStatus> productStatus) {
		this.productStatus = productStatus;
	}

}