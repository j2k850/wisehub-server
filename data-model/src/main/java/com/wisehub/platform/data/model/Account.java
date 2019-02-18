package com.wisehub.platform.data.model;

import java.util.Date;
import java.util.UUID;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
@Table(name = "accounts", keyspace = "platform")
public class Account implements VersionPM{

	@ApiModelProperty(value = "The database generated account ID", readOnly = true, required=false, name = "account_id")
	@JsonProperty(value="account_id")
	@PartitionKey
	@Column(name = "account_id")
	private UUID id;

	@ApiModelProperty(value = "The x of account", name = "branch_id")
	@JsonProperty(value="branch_id")
	@Column(name = "branch_id")
	private UUID branchId;
	
	@ApiModelProperty(value = "The x of account", name = "user_id")
	@JsonProperty(value="user_id")
	@Column(name = "user_id")
	private UUID userId;

	@ApiModelProperty(value = "The number of account", name = "account_number")
	@JsonProperty(value="account_number")
	@Column(name = "account_number")
	private Long number;

	@ApiModelProperty(value = "The name of account", name = "account_name")
	@JsonProperty(value="account_name")
	@Column(name = "account_name")
	private String name;

	@ApiModelProperty(value = "The type of account", name = "account_type")
	@JsonProperty(value="account_type")
	@Column(name = "account_type")
	private AccountType type;

	@ApiModelProperty(value = "The status of account", name = "account_status")
	@JsonProperty(value="account_status")
	@Column(name = "account_status")
	private AccountStatus status;

	@ApiModelProperty(value = "created at account", readOnly = true, required=false, name = "created_at")
	@JsonProperty(value="created_at")
	@Column(name = "created_at")
	private Date createdAt;

	@ApiModelProperty(value = "update at account", readOnly = true, required=false, name = "updated_at")
	@JsonProperty(value="updated_at")
	@Column(name = "updated_at")
	private Date updatedAt;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getBranchId() {
		return branchId;
	}

	public void setBranchId(UUID branchId) {
		this.branchId = branchId;
	}

	public UUID getUserId() {
		return userId;
	}
	
	public void setUserId(UUID userId) {
		this.userId = userId;
	}
	
	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AccountType getType() {
		return type;
	}

	public void setType(AccountType type) {
		this.type = type;
	}

	public AccountStatus getStatus() {
		return status;
	}

	public void setStatus(AccountStatus status) {
		this.status = status;
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

	public static void main(String[] args) {
		
		for (int i = 0; i < 10; i++) {
			System.out.println(UUID.randomUUID());
			
		}
		
	}
}