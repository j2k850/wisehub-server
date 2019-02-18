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
@Table(name = "branches", keyspace = "platform")
public class Branch implements VersionPM{

    @ApiModelProperty(value ="The database generated user ID",  name = "branch_id")
	@PartitionKey
	@Column(name = "branch_id")
    private UUID id;

	@ApiModelProperty( name = "fin_inst_id")
	@Column(name = "fin_inst_id")
	private UUID finInstId;

	@ApiModelProperty( name = "branch_name")
	@Column(name = "branch_name")
	private String name;

	@ApiModelProperty(name = "branch_mgr")
	@Column(name = "branch_mgr")
	private String mgr;

	@ApiModelProperty(name = "branch_mgr_email")
	@Column(name = "branch_mgr_email")
	private String mgrEmail;

    @ApiModelProperty(value ="Addresses list", dataType = "Map")
	@FrozenValue
	@Column(name = "addresses")
	private Map<String, UDTAddress> addresses;

	@ApiModelProperty(value = "The database generated account ID", readOnly = true, required=false, name = "account_id")
	@Column(name = "created_at")
	private Date createdAt;

	@ApiModelProperty(value = "The database generated account ID", readOnly = true, required=false, name = "account_id")
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

	public String getMgr() {
		return mgr;
	}

	public void setMgr(String mgr) {
		this.mgr = mgr;
	}

	public String getMgrEmail() {
		return mgrEmail;
	}

	public void setMgrEmail(String email) {
		this.mgrEmail = email;
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