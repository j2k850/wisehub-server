package com.wisehub.platform.api.domain.model;

import java.util.Date;

public class CustomerNotificationDomainModel {

	private String id;
	private String notificationId;
	private NotificationStatus status;
	private Date receive;
	private String selectedAnswer;

	public CustomerNotificationDomainModel() {
	}

	public CustomerNotificationDomainModel(String id, NotificationStatus status, Date receive, String notificationId, String selectedAnswer) {
		super();
		this.id = id;
		this.notificationId = notificationId;
		this.status = status;
		this.receive = receive;
		this.selectedAnswer = selectedAnswer;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(String notificationId) {
		this.notificationId = notificationId;
	}

	public NotificationStatus getStatus() {
		return status;
	}

	public void setStatus(NotificationStatus status) {
		this.status = status;
	}

	public Date getReceive() {
		return receive;
	}

	public void setReceive(Date receive) {
		this.receive = receive;
	}

	public String getSelectedAnswer() {
		return selectedAnswer;
	}

	public void setSelectedAnswer(String selectedAnswer) {
		this.selectedAnswer = selectedAnswer;
	}

}
