package com.wisehub.platform.data.model;

import java.util.Date;
import java.util.UUID;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
@Table(name = "dashboard_user_roles", keyspace = "platform")
public class DashboardUserRole {

	@ApiModelProperty(value = "The database generated user ID", name = "dashboard_user_id ")
	@PartitionKey(value=0)
	@Column(name = "dashboard_user_id")
	private UUID dashboardUserId;

	@ApiModelProperty(value = "The database generated user ID", name = "dashboard_role_id ")
	@PartitionKey(value=1)
	@Column(name = "dashboard_role_id")
	private UUID dashboardRoleId;

	@ApiModelProperty(value = "The auto-generated created at date", readOnly = true, required=false)
	@Column(name = "created_at")
	private Date createdAt;

	@ApiModelProperty(value = "The auto-generated updated at date", readOnly = true, required=false)
	@Column(name = "updated_at")
	private Date updatedAt;

	public UUID getDashboardUserId() {
		return dashboardUserId;
	}

	public void setDashboardUserId(UUID dashboardUserId) {
		this.dashboardUserId = dashboardUserId;
	}

	public UUID getDashboardRoleId() {
		return dashboardRoleId;
	}

	public void setDashboardRoleId(UUID dashboardRoleId) {
		this.dashboardRoleId = dashboardRoleId;
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