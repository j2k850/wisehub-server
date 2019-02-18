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
@Table(name = "users", keyspace = "platform")
public class User implements VersionPM {

	@ApiModelProperty(value = "The database generated user ID", name = "user_id")
	@PartitionKey
	@Column(name = "user_id")
	private UUID id;

	@ApiModelProperty(value = "The bvn of user", dataType = "Long")
	@Column
	private Long bvn;

	@ApiModelProperty(value = "The fullname of user", dataType = "Map")
	@Column
	private UDTFullName name;

	@ApiModelProperty(value = "The email of user", dataType = "String")
	@Column
	private String email;

	@ApiModelProperty(value = "Addresses list", dataType = "Map")
	@FrozenValue
	@Column(name = "addresses")
	private Map<String, UDTAddress> addresses;

	@ApiModelProperty(value = "The auto-generated created at date", readOnly = true, required=false)
	@Column(name = "created_at")
	private Date createdAt;

	@ApiModelProperty(value = "The auto-generated updated at date", readOnly = true, required=false)
	@Column(name = "updated_at")
	private Date updatedAt;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Long getBvn() {
		return bvn;
	}

	public void setBvn(Long bvn) {
		this.bvn = bvn;
	}

	public UDTFullName getName() {
		return name;
	}

	public void setName(UDTFullName name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	@Override
	public String toString() {
		return "User [" + (id != null ? "id=" + id + ", " : "") + (bvn != null ? "bvn=" + bvn + ", " : "") + (name != null ? "name=" + name + ", " : "")
				+ (email != null ? "email=" + email + ", " : "") + (addresses != null ? "addresses=" + addresses + ", " : "")
				+ (createdAt != null ? "createdAt=" + createdAt + ", " : "") + (updatedAt != null ? "updatedAt=" + updatedAt + ", " : "")
				+ (getClass() != null ? "getClass()=" + getClass() + ", " : "") + "hashCode()=" + hashCode() + ", "
				+ (super.toString() != null ? "toString()=" + super.toString() : "") + "]";
	}

}