package com.wisehub.platform.data.model;

import com.datastax.driver.mapping.annotations.Field;
import com.datastax.driver.mapping.annotations.UDT;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "rules")
@UDT(name = "rules", keyspace = "platform")
public class UDTRules {

	@ApiModelProperty(value = "The name of rules", dataType = "String")
	@Field(name = "rule_name")
	private String name;

	@ApiModelProperty(value = "The description of rules", dataType = "String")
	@Field(name = "rule_description")

	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
