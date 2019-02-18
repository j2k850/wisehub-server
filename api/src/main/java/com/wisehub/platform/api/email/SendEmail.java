package com.wisehub.platform.api.email;

import java.util.UUID;

public class SendEmail {
	
	private UUID id;
	private String bank;
	private String fromName;
	private String fromEmail;
	private String subject;
	private String recps;
	private String recpName;
	private String templateId;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public String getFromEmail() {
		return fromEmail;
	}

	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getRecps() {
		return recps;
	}

	public void setRecps(String recps) {
		this.recps = recps;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getRecpName() {
		return recpName;
	}

	public void setRecpName(String recpName) {
		this.recpName = recpName;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	@Override
	public String toString() {
		return "SendEmail [id=" + id + ", bank=" + bank + ", fromName=" + fromName + ", fromEmail=" + fromEmail
				+ ", subject=" + subject + ", recps=" + recps + ", recpName=" + recpName + ", templateId=" + templateId
				+ "]";
	}
}
