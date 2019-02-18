package com.wisehub.platform.data.producer.fake.model;

import java.util.Arrays;
import java.util.Map;

import com.google.common.base.Function;
import com.google.common.collect.Maps;

public enum TransactionType {
	
	OPEN(1, "Opening Balance"), 
	WITHDRAW(2, "Withdraw"), 
	DEPOSIT_INFO(3, "Deposit Info"),
	INCOME_BY_DAY(4, "Income by day");

	final private int value;
	final private String description;

	private TransactionType(int value, String description) {
		this.description = description;
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static TransactionType byValue(int value) {
		return NAME_LOOKUP.get(value);
	}

	public String getDescription() {
		return description;
	}

	public static TransactionType byName(String name) {
		return NAME_LOOKUP.get(name);
	}

	private static final Function<TransactionType, String> NAME_FUNCTION = new Function<TransactionType, String>() {
		@Override
		public String apply(TransactionType status) {
			return status.getDescription();
		}
	};

	private static final Map<String, TransactionType> NAME_LOOKUP = Maps.uniqueIndex(Arrays.asList(TransactionType.class.getEnumConstants()), NAME_FUNCTION);

}