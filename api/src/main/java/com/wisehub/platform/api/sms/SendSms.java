package com.wisehub.platform.api.sms;

import java.util.UUID;

public class SendSms {
	
	private UUID id;
	private String toName;
	private String bank;
	private String toPhoneNumber;
	private String subject;
	private String templateId;
	private String insuranceAmount;
	private String creditLineAmount;
	private String investmentAmount;
	private String yearlyYield;
	private String yield;
	private String loanAmount;
	private String account;
	private String available;
	private String saveAmount;
	private String monthlyAmount;
	private String yearlySavings;
	private String amount;
	private String due;
	private String airtimeAmount;
	

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getToPhoneNumber() {
		return toPhoneNumber;
	}

	public void setToNumber(String toPhoneNumber) {
		this.toPhoneNumber = toPhoneNumber;
	}

	public String getToName() {
		return toName;
	}

	public void setToName(String toName) {
		this.toName = toName;
	}
	
	public String getInsuranceAmount() {
		return insuranceAmount;
	}

	public void setInsuranceAmount(String insuranceAmount) {
		this.insuranceAmount = insuranceAmount;
	}

	public String getCreditLineAmount() {
		return creditLineAmount;
	}

	public void setCreditLineAmount(String creditLineAmount) {
		this.creditLineAmount = creditLineAmount;
	}

	public String getInvestmentAmount() {
		return investmentAmount;
	}

	public void setInvestmentAmount(String investmentAmount) {
		this.investmentAmount = investmentAmount;
	}

	public String getYearlyYield() {
		return yearlyYield;
	}

	public void setYearlyYield(String yearlyYield) {
		this.yearlyYield = yearlyYield;
	}

	public String getYield() {
		return yield;
	}

	public void setYield(String yield) {
		this.yield = yield;
	}

	public String getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}

	public String getSaveAmount() {
		return saveAmount;
	}

	public void setSaveAmount(String saveAmount) {
		this.saveAmount = saveAmount;
	}

	public String getMonthlyAmount() {
		return monthlyAmount;
	}

	public void setMonthlyAmount(String monthlyAmount) {
		this.monthlyAmount = monthlyAmount;
	}

	public String getYearlySavings() {
		return yearlySavings;
	}

	public void setYearlySavings(String yearlySavings) {
		this.yearlySavings = yearlySavings;
	}

	public String getDue() {
		return due;
	}

	public void setDue(String due) {
		this.due = due;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getAirtimeAmount() {
		return airtimeAmount;
	}

	public void setAirtimeAmount(String airtimeAmount) {
		this.airtimeAmount = airtimeAmount;
	}

}
