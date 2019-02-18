package com.wisehub.platform.data.model;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import com.datastax.driver.mapping.annotations.ClusteringColumn;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
@Table(name = "correspondence_by_user", keyspace = "platform")
public class CorrespondenceByUser {

	@ApiModelProperty(value = "The database generated user ID", name = "user_id")
	@PartitionKey
	@Column(name = "user_id")
	private UUID userId;

	@ApiModelProperty(readOnly = true, required=false, name = "created_at")
	@ClusteringColumn(value = 0)
	@Column(name = "created_at")
	private Date createdAt;

	@ApiModelProperty(readOnly = true, required=false, name = "event")
	@ClusteringColumn(value = 1)
	@Column
	private String event;

	@ApiModelProperty(value = "The database generated account ID", name = "account_id")
	@Column
	private Map<String, UDTCorrespondence> correspondence;

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

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public Map<String, UDTCorrespondence> getCorrespondence() {
		return correspondence;
	}

	public void setCorrespondence(Map<String, UDTCorrespondence> correspondence) {
		this.correspondence = correspondence;
	}

}