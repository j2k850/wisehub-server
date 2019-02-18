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
@Table(name = "financial_institutions", keyspace = "platform")
public class FinancialInstitution implements VersionPM {

	@ApiModelProperty(value = "The database generated user ID", name = "fin_inst_id")
	@PartitionKey
	@Column(name = "fin_inst_id")
	private UUID id;

	@ApiModelProperty(value = "The database generated account ID", name = "financial_institution")
	@Column(name = "financial_institution")
	private String financialInstitution;

	@ApiModelProperty(value = "Addresses list")
	@FrozenValue
	@Column(name = "addresses")
	private Map<String, UDTAddress> addresses;

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

	public String getFinancialInstitution() {
		return financialInstitution;
	}

	public void setFinancialInstitution(String financialInstitution) {
		this.financialInstitution = financialInstitution;
	}

	public Map<String, UDTAddress> getAddresses() {
		return addresses;
	}

	public void setAddresses(Map<String, UDTAddress> addresses) {
		this.addresses = addresses;
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