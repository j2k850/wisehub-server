package com.wisehub.platform.data.model;

import java.util.Date;
import java.util.UUID;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
@Table(name = "dashboard_roles", keyspace = "platform")
public class DashboardRole implements VersionPM {

	@ApiModelProperty(value = "The database generated user ID", name = "dashboard_role_id")
	@PartitionKey
	@Column(name = "dashboard_role_id")
	private UUID id;

	@ApiModelProperty(dataType = "role_name")
	@Column(name = "dashboard_name")
	private String roleName;

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

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String bvn) {
		this.roleName = bvn;
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
		return "DashboardRole [" + (id != null ? "id=" + id + ", " : "") + (roleName != null ? "roleName=" + roleName + ", " : "")
				+ (createdAt != null ? "createdAt=" + createdAt + ", " : "")
				+ (updatedAt != null ? "updatedAt=" + updatedAt + ", " : "") + (getClass() != null ? "getClass()=" + getClass() + ", " : "") + "hashCode()="
				+ hashCode() + ", " + (super.toString() != null ? "toString()=" + super.toString() : "") + "]";
	}

}