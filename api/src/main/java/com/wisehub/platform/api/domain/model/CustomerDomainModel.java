package com.wisehub.platform.api.domain.model;

import java.util.List;
import java.util.UUID;

import java.util.Date;

public class CustomerDomainModel {

	private UUID id;
	private String fullname;
	private Date createAt;
	private CustomerStatus status;
	
	private List<CustomerNotificationDomainModel> notifications;
	
	private List<ProfitDomainModel> profits;

	public CustomerDomainModel() {
	}
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public CustomerStatus getStatus() {
		return status;
	}

	public void setStatus(CustomerStatus status) {
		this.status = status;
	}

	public List<CustomerNotificationDomainModel> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<CustomerNotificationDomainModel> notifications) {
		this.notifications = notifications;
	}

	public List<ProfitDomainModel> getProfits() {
		return profits;
	}

	public void setProfits(List<ProfitDomainModel> profits) {
		this.profits = profits;
	}

}
