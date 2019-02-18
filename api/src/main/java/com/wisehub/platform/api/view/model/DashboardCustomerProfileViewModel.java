package com.wisehub.platform.api.view.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wisehub.platform.data.model.DashboardUser;

/**
 * Customer Lifetime Value Number of Accounts Number of Sales Number of Engagements Number of Sales pending Earnings -
 * Simple shows total money earned on customer First Sale - Date of first sale Last Sale - Dale of last sale First
 * Engagement - Date of first engagement Last Engagement - Date of last engagement Below this section lets have a tabs
 * panel with headings for 'Timeline', 'Events', 'Finances', 'Sales'. For finances and sales tab we can show info in
 * Customer Finance and Customer Sales screen. Timeline will be show key milestones. Events will show recorded events.
 * 
 * @author sstjerne
 *
 */
public class DashboardCustomerProfileViewModel {

	private DashboardUser user;

	@JsonProperty(value = "clv")
	private BigDecimal clv;

	private Long accounts;
	private Long sales;
	@JsonProperty(value = "pending_sales")
	private Long pendingSales;

	@JsonProperty
	private RevenueViewModel revenue;

	@JsonProperty(value = "first_sale")
	private SaleAccountViewModel firstSale;
	@JsonProperty(value = "last_sale")
	private SaleAccountViewModel lastSale;

	@JsonProperty(value = "first_engagement")
	private EngagementViewModel firstEngagement;
	@JsonProperty(value = "last_engagement")
	private EngagementViewModel lastEngagement;

	public void setUser(DashboardUser user) {
		this.user = user;
	}

	public DashboardUser getUser() {
		return user;
	}

	public BigDecimal getClv() {
		return clv;
	}

	public void setClv(BigDecimal clv) {
		this.clv = clv;
	}

	public Long getAccounts() {
		return accounts;
	}

	public void setAccounts(Long accounts) {
		this.accounts = accounts;
	}

	public Long getSales() {
		return sales;
	}

	public void setSales(Long sales) {
		this.sales = sales;
	}

	public Long getPendingSales() {
		return pendingSales;
	}

	public void setPendingSales(Long pendinSales) {
		this.pendingSales = pendinSales;
	}

	public RevenueViewModel getRevenue() {
		return revenue;
	}

	public void setRevenue(RevenueViewModel revenue) {
		this.revenue = revenue;
	}

	public SaleAccountViewModel getFirstSale() {
		return firstSale;
	}

	public void setFirstSale(SaleAccountViewModel firstSale) {
		this.firstSale = firstSale;
	}

	public SaleAccountViewModel getLastSale() {
		return lastSale;
	}

	public void setLastSale(SaleAccountViewModel lastSale) {
		this.lastSale = lastSale;
	}

	public EngagementViewModel getFirstEngagement() {
		return firstEngagement;
	}

	public void setFirstEngagement(EngagementViewModel firstEngagement) {
		this.firstEngagement = firstEngagement;
	}

	public EngagementViewModel getLastEngagement() {
		return lastEngagement;
	}

	public void setLastEngagement(EngagementViewModel lastEngagement) {
		this.lastEngagement = lastEngagement;
	}

}
