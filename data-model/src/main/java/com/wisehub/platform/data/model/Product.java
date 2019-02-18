package com.wisehub.platform.data.model;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.FrozenValue;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
@Table(name = "products", keyspace = "platform")

public class Product implements VersionPM{

	@ApiModelProperty(value = "The database generated user ID", name = "product_id")
	@PartitionKey
	@Column(name = "product_id")
	private UUID id;

	@ApiModelProperty(value = "The database generated account ID", name = "fin_inst_id")
	@Column(name = "fin_inst_id")
	private UUID finInstId;

	@ApiModelProperty(value = "The database generated account ID", name = "product_name")
	@Column(name = "product_name")
	private String name;

	@ApiModelProperty(value = "rules list", dataType = "Map")
	@FrozenValue
	@Column(name = "rules")
	private Map<String, UDTRules> rules;

	@ApiModelProperty(value = "The database generated account ID", name = "created_at")
	@Column(name = "created_at")
	private Date createdAt;

	@ApiModelProperty(value = "The database generated account ID", name = "updated_at")
	@Column(name = "updated_at")
	private Date updatedAt;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getFinInstId() {
		return finInstId;
	}

	public void setFinInstId(UUID finInstId) {
		this.finInstId = finInstId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, UDTRules> getRules() {
		return rules;
	}

	public void setRules(Map<String, UDTRules> rules) {
		this.rules = rules;
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