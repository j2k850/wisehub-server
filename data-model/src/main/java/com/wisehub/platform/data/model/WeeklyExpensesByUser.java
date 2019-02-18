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
@Table(name = "weekly_expenses_by_user", keyspace = "platform")

public class WeeklyExpensesByUser {

	@ApiModelProperty(name = "yyyymmdd")
	@Column(name = "yyyymmdd")
	@PartitionKey
	private String yyyymmdd;

	@ApiModelProperty(name = "created_at")
	@ClusteringColumn(value = 0)
	@Column(name = "created_at")
	private Date createdAt;

	@ApiModelProperty(name = "user_id")
	@ClusteringColumn(value = 1)
	@Column(name = "user_id")
	private UUID userId;

	@ApiModelProperty(value = "spent list")
	@FrozenValue
	@Column(name = "spent")
	private Map<String, UDTTxCategoryAmount> spent;

	public String getYyyymmdd() {
		return yyyymmdd;
	}

	public void setYyyymmdd(String yyyymmdd) {
		this.yyyymmdd = yyyymmdd;
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

	public Map<String, UDTTxCategoryAmount> getSpent() {
		return spent;
	}

	public void setSpent(Map<String, UDTTxCategoryAmount> spent) {
		this.spent = spent;
	}

}