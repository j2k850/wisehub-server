package com.wisehub.platform.data.model;

import java.util.UUID;

import com.datastax.driver.mapping.annotations.ClusteringColumn;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
@Table(name = "dashboard_event", keyspace = "platform")
public class DashboardEvent {

	@ApiModelProperty(value = "The database generated user ID", name = "user_id")
	@PartitionKey
	@Column(name = "user_id")
	private UUID userId;

	@ApiModelProperty(readOnly = true, required=false, name = "event_timestamp")
	@ClusteringColumn(value = 0)
	@Column(name = "event_timestamp")
	private UUID eventTimestamp;

	@ApiModelProperty(readOnly = true, required=false, name = "event")
	@ClusteringColumn(value = 1)
	@Column(name = "event")
	private String event;

	@ApiModelProperty(value = "The database generated account ID", name = "message")
	@Column
	private String message;

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public UUID getEventTimestamp() {
		return eventTimestamp;
	}

	public void setEventTimestamp(UUID eventTimestamp) {
		this.eventTimestamp = eventTimestamp;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}