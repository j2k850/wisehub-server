package com.wisehub.platform.data.model.dao;

public class DTOParameter {

	private Integer items = 10;
	private Integer start = 0;

	private String yyyymmdd;
	private Long accountId;
	private Long accountNumber;
	private String userId;
	private Long bvn;
	private String createdAt;
	private String productId;
	private String financialInstitutionId;
	private String event;

	public DTOParameter() {
	}

	public Integer getItems() {
		return items;
	}

	public void setItems(Integer items) {
		this.items = items;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createAt) {
		this.createdAt = createAt;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setFinancialInstitutionId(String financialInstitutionId) {
		this.financialInstitutionId = financialInstitutionId;
	}

	public String getFinancialInstitutionId() {
		return financialInstitutionId;
	}

	public void setYyyymmdd(String yyyymmdd) {
		this.yyyymmdd = yyyymmdd;
	}

	public String getYyyymmdd() {
		return yyyymmdd;
	}

	public void setBvn(Long bvn) {
		this.bvn = bvn;
	}

	public Long getBvn() {
		return bvn;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}
}
