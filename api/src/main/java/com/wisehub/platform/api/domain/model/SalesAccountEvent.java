package com.wisehub.platform.api.domain.model;

import java.util.Arrays;
import java.util.Map;

import com.google.common.base.Function;
import com.google.common.collect.Maps;

public enum SalesAccountEvent {

	sales_opened("Sales Opened"), 
	insufficient_funds_email_sent("Insufficient funds email sent"), 
	weekly_summary_email_sent("Weekly summary email Sent"), 
	sale_recommened("Sale Recommened"), 
	recommend_creditcard_sms_sent("Recommend creditcard sms sent"), 
	recommend_savings_sms_sent("Recommend savings sms sent"),
	recommend_loan_sms_sent("Recommend loan sms sent"),
	recommend_overdraft_sms_sent("Recommend overdraft sms sent"),
	recommend_insurance_sms_sent("Recommend insurance sms sent"),
	recommend_investments_sms_sent("Recommend investments sms sent"),
	reminder_creditcard_sms_sent("Reminder creditcard sms sent"), 
	sale_pending("Sale Pending"), 
	reminder_savings_sms_sent("Reminder savings sms sent"),
	reminder_loan_sms_sent("Reminder loan sms sent"),
	reminder_overdraft_sms_sent("Reminder overdraft sms sent"),
	reminder_insurance_sms_sent("Reminder insurance sms sent"),
	reminder_investments_sms_sent("Reminder investments sms sent"),
	sale_closed("Sale Closed"),
	;
	
	
	final private String name;
	
	private SalesAccountEvent(String name) {
		this.name = name;
	}

	
	public static SalesAccountEvent byValue(int value) {
		return NAME_LOOKUP.get(value);
	}

	public String getName() {
		return name;
	}

	public static SalesAccountEvent byName(String name) {
		return NAME_LOOKUP.get(name);
	}

	private static final Function<SalesAccountEvent, String> NAME_FUNCTION = new Function<SalesAccountEvent, String>() {
		@Override
		public String apply(SalesAccountEvent status) {
			return status.toString();
		}
	};

	public static final Map<String, SalesAccountEvent> NAME_LOOKUP = 
			Maps.uniqueIndex(Arrays.asList(SalesAccountEvent.class.getEnumConstants()), 
					NAME_FUNCTION);

	
}
