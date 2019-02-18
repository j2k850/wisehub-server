package com.wisehub.platform.api.view.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wisehub.platform.api.dto.DTODashoard;

@JsonInclude(Include.NON_NULL)
public class CustomerViewModel {

	@JsonProperty(value = "filter")
	private DTODashoard filter;

	@JsonProperty(value = "customers_notified")
	private Long customersNotified;

	@JsonProperty(value = "avg_frequency_of_notification")
	private BigDecimal avgFrequencyOfNotification;

	@JsonProperty(value = "customers_responded")
	private Long customersResponded;

	@JsonProperty(value = "customers_pending")
	private Long customersPending;

	@JsonProperty(value = "customers_newly_active")
	private Long customersNewlyActive;

	@JsonProperty(value = "top_customers_feedback")
	private List<FeedbackHitViewModel> topCustomersFeedback;

	@JsonProperty(value = "avg_customer_clv")
	private List<CustomerCLVViewModel> avgCustomerCLV;

	@JsonProperty(value = "top_customers_clv")
	private List<CustomerCLVViewModel> topCustomerCLV;

	public void setFilter(DTODashoard filter) {
		this.filter = filter;
	}

	public DTODashoard getFilter() {
		return filter;
	}

	public Long getCustomersNotified() {
		return customersNotified;
	}

	public void setCustomersNotified(Long customersNotified) {
		this.customersNotified = customersNotified;
	}

	public BigDecimal getAvgFrequencyOfNotification() {
		return avgFrequencyOfNotification;
	}

	public void setAvgFrequencyOfNotification(BigDecimal avgFrequencyOfNotication) {
		this.avgFrequencyOfNotification = avgFrequencyOfNotication;
	}

	public Long getCustomersResponded() {
		return customersResponded;
	}

	public void setCustomersResponded(Long customersResponded) {
		this.customersResponded = customersResponded;
	}

	public Long getCustomersPending() {
		return customersPending;
	}

	public void setCustomersPending(Long customersPending) {
		this.customersPending = customersPending;
	}

	public Long getCustomersNewlyActive() {
		return customersNewlyActive;
	}

	public void setCustomersNewlyActive(Long customersNewlyActive) {
		this.customersNewlyActive = customersNewlyActive;
	}

	public List<FeedbackHitViewModel> getTopCustomersFeedback() {
		return topCustomersFeedback;
	}

	public void setTopCustomersFeedback(List<FeedbackHitViewModel> topCustomersFeedback) {
		this.topCustomersFeedback = topCustomersFeedback;
	}

	public List<CustomerCLVViewModel> getAvgCustomerCLV() {
		return avgCustomerCLV;
	}

	public void setAvgCustomerCLV(List<CustomerCLVViewModel> avgCustomerCLV) {
		this.avgCustomerCLV = avgCustomerCLV;
	}

	public List<CustomerCLVViewModel> getTopCustomerCLV() {
		return topCustomerCLV;
	}

	public void setTopCustomerCLV(List<CustomerCLVViewModel> topCustomerCLV) {
		this.topCustomerCLV = topCustomerCLV;
	}

}
