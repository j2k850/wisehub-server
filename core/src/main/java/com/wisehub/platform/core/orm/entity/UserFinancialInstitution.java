package com.wisehub.platform.core.orm.entity;

public class UserFinancialInstitution {

	private String userId;
	private String financialInstitutionId;

	public UserFinancialInstitution(String userId, String financialInstitutionId) {
		this.userId = userId;
		this.financialInstitutionId = financialInstitutionId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFinancialInstitutionId() {
		return this.financialInstitutionId;
	}

	public void setFinancialInstitutionId(String financialInstitutionId) {
		this.financialInstitutionId = financialInstitutionId;
	}

}
