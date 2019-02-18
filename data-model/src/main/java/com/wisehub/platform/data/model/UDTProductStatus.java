package com.wisehub.platform.data.model;

import java.util.Date;

import com.datastax.driver.mapping.annotations.Field;
import com.datastax.driver.mapping.annotations.UDT;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "product_status")
@UDT(name = "product_status", keyspace = "platform")
public class UDTProductStatus {

	@ApiModelProperty(value = "The event of correspondence", dataType = "String")
	@Field
	private String status;

	@ApiModelProperty(value = "The event of correspondence", dataType = "Timestamp")
	@Field
	private Date created_at;

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

}
