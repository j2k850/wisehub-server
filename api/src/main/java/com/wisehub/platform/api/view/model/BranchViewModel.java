package com.wisehub.platform.api.view.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BranchViewModel {

	@JsonProperty("employee_first_name")
	private String firstname;

	@JsonProperty("employee_last_name")
	private String lastname;

	@JsonProperty("employee_subject")
	private String subject;

	@JsonProperty("bank")
	private String bank;

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getSubject() {
		return subject;
	}

	public String getBank() {
		return bank;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

}
