package com.wisehub.platform.data.model;

import java.util.Date;
import java.util.UUID;

import com.datastax.driver.mapping.annotations.ClusteringColumn;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
@Table(name = "user_fin_insts", keyspace = "platform")
public class UserFinancialInstitution {

	@ApiModelProperty(name = "user_id")
	@PartitionKey
	@Column(name = "user_id")
	private UUID userId;

	@ApiModelProperty(name = "created_at")
	@ClusteringColumn(value = 0)
	@Column(name = "created_at")
	private Date createdAt;

	@ApiModelProperty(name = "fin_inst_id")
	@Column(name = "fin_inst_id")
	@ClusteringColumn(value = 1)
	private UUID finInstId;

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public UUID getFinInstId() {
		return finInstId;
	}

	public void setFinInstId(UUID finInstId) {
		this.finInstId = finInstId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createAt) {
		this.createdAt = createAt;
	}

}