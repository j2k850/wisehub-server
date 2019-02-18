package com.wisehub.platform.api.view.model;

import java.util.UUID;

import java.util.Date;

public class EngagementViewModel {

	private UUID id;
	private Date date;

	public EngagementViewModel() {
	}

	public EngagementViewModel(UUID id, Date date) {
		super();
		this.id = id;
		this.date = date;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
