package com.wisehub.platform.data.model;

import java.util.Arrays;
import java.util.Map;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.wisehub.platform.data.model.serializer.AccountTypeDeserializer;
import com.wisehub.platform.data.model.serializer.AccountTypeSerializer;

@JsonSerialize(using = AccountTypeSerializer.class)
@JsonDeserialize(using = AccountTypeDeserializer.class)
public enum AccountType {


	UNKNOWN("Unknown", 1, true, true),
	CHECKING("Checking", 2, true, true),
	MONEY_MARKET("Money Market", 3, true, true),
	CREDIT_CARD("Credit Card", 4, true, true),
	SAVINGS("Savings", 5, true, true),
	CREDIT_LINE("Credit Line", 7, true, true),
	BROKERAGE("Brokerage", 8,true, true),
	CASH("Cash", 9, false,false),
	MANUAL("Manual", 10, true,false),
	INVESTMENT("Investment", 11,true, true),
	CERTIFICATE("Certificate of Deposit", 12,true, true),
	LOAN("Loan", 13,true, true),
	MORTGAGE("Mortgage", 14,true, true),	
	ASSET("Asset", 15,true, true),
	EQUITY("Equity", 16, true, true);

	final private String description;
	final private int value;
	final private boolean hasBalance;
	final private boolean hasUploads;

	private AccountType(String description, int value, boolean hasBalance, boolean hasUploads) {
		this.description  = description;
		this.value = value;
		this.hasBalance = hasBalance;
		this.hasUploads = hasUploads;
	}
	
	public int getValue() {
		return value;
	}

	public static AccountType byValue(int value) {
		return NAME_LOOKUP.get(value);
	}
	
	public String getDescription() {
		return description;
	}
	
	public static AccountType byName(String name) {
		return NAME_LOOKUP.get(name.toUpperCase());
	}
	
	
	
	private static final Function<AccountType, String> NAME_FUNCTION =
		new Function<AccountType, String>(){
			@Override
			public String apply(AccountType status) {
				return status.toString();
			}
		};

	private static final Map<String, AccountType> NAME_LOOKUP =
		Maps.uniqueIndex(
			Arrays.asList(AccountType.class.getEnumConstants()),
			NAME_FUNCTION
		);

	public boolean hasBalance() {
		return hasBalance;
	}
	
	public boolean hasUploads() {
		return hasUploads;
	}
	
	public boolean hasContinuousBalance() {
		return hasBalance && !hasUploads;
	}
	
	
}
