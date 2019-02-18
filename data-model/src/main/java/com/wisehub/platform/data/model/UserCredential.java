package com.wisehub.platform.data.model;

import java.util.UUID;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
@Table(name = "user_credentials", keyspace = "platform")
public class UserCredential {

	@ApiModelProperty(name = "email", required = true)
	@PartitionKey
	@Column(name = "email")
	private String email; // alias email

	@ApiModelProperty(value = "The database generated account ID", name = "user_id")
	@Column(name = "user_id")
	private UUID userId;

	@ApiModelProperty(value = "The database generated account ID", name = "password")
	@Column(name = "password")
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String id) {
		this.email = id;
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}

}