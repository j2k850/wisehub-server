package com.wisehub.platform.data.model;

import com.datastax.driver.mapping.annotations.Field;
import com.datastax.driver.mapping.annotations.UDT;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "fullname")
@UDT(name = "fullname", keyspace = "platform")
public class UDTFullName {

	@ApiModelProperty(value ="The first name of user", dataType = "String", name = "first_name")
	@Field(name = "first_name")
	private String firstName;

	@ApiModelProperty(value ="The last name of user", dataType = "String", name = "last_name")
	@Field(name = "last_name")
	private String lastName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "UDTFullName [" + (firstName != null ? "firstName=" + firstName + ", " : "") + (lastName != null ? "lastName=" + lastName : "") + "]";
	}
	
	

}
