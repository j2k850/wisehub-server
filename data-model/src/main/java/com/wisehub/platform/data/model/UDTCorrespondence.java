package com.wisehub.platform.data.model;

import java.util.UUID;

import com.datastax.driver.mapping.annotations.Field;
import com.datastax.driver.mapping.annotations.UDT;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "correspondence")
@UDT(name = "correspondence", keyspace = "platform")
public class UDTCorrespondence {

	@ApiModelProperty(value = "The event of correspondence", dataType = "String")
	@Field
	private String event;

	@ApiModelProperty(value = "The type of correspondence", dataType = "String")
	@Field
	private String type;

	@ApiModelProperty(value = "The description of correspondence", dataType = "String")
	@Field
	private String description;

	@ApiModelProperty(value = "The street of correspondence", dataType = "uuid")
	@Field
	private UUID created_at;

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UUID getCreated_at() {
		return created_at;
	}

	public void setCreated_at(UUID created_at) {
		this.created_at = created_at;
	}

}
