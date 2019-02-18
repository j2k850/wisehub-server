package com.wisehub.platform.api.view.model;

public class Message {

	private String content;
	private Integer seconds;
	private Integer amount;

	public Message() {
	}

	public Message(String content) {
		this.content = content;
	}
	
	public Message(String content, Integer seconds, Integer amount) {
		super();
		this.content = content;
		this.seconds = seconds;
		this.amount = amount;
	}

	public String getContent() {
		return content;
	}

	public Integer getSeconds() {
		return seconds;
	}

	public void setSeconds(Integer seconds) {
		this.seconds = seconds;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public void setContent(String content) {
		this.content = content;
	}

}