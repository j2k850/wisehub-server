package com.wisehub.platform.data.producer.client;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class DashboardRegisterViewModel {

	@ApiModelProperty(name = "email", required = true)
	private String email; 

	@ApiModelProperty(name = "username", required = true)
	private String username; 

	@ApiModelProperty(value = "The database generated account ID", name = "password")
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}