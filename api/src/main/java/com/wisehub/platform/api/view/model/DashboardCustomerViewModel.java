package com.wisehub.platform.api.view.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DashboardCustomerViewModel {

	private String username;
	private String firstname;
	private String lastname;

	@JsonProperty(value = "products_recommended")
	private List<ProductRecommendedViewModel> productRecommended;

	@JsonProperty(value = "accounts_info")
	private List<AccountInfoViewModel> accountInfo;

	@JsonProperty
	private RevenueViewModel revenue;

	@JsonProperty
	private BranchViewModel branch;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public List<ProductRecommendedViewModel> getProductRecommended() {
		return productRecommended;
	}

	public void setProductRecommended(List<ProductRecommendedViewModel> productRecommended) {
		this.productRecommended = productRecommended;
	}

	public List<AccountInfoViewModel> getAccountInfo() {
		return accountInfo;
	}

	public void setAccountInfo(List<AccountInfoViewModel> accountInfo) {
		this.accountInfo = accountInfo;
	}

	public RevenueViewModel getRevenue() {
		return revenue;
	}

	public void setRevenue(RevenueViewModel revenue) {
		this.revenue = revenue;
	}

	public BranchViewModel getBranch() {
		return branch;
	}

	public void setBranch(BranchViewModel branch) {
		this.branch = branch;
	}

}
