package com.wisehub.platform.api.domain.model;

import java.util.Arrays;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.base.Function;
import com.google.common.collect.Maps;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum CustomerStatus {

	PENDING(0, "Pending"),
	ACTIVE(1, "Active"),
	PASIVE(2, "Pasive"), 
	NEW(3, "New");

	final private int id;
	final private String name;

	private CustomerStatus(int value, String name) {
		this.name = name;
		this.id = value;
	}

	public int getId() {
		return id;
	}

	public static CustomerStatus byValue(int value) {
		return NAME_LOOKUP.get(value);
	}

	public String getName() {
		return name;
	}

	public static CustomerStatus byName(String name) {
		return NAME_LOOKUP.get(name);
	}

	private static final Function<CustomerStatus, String> NAME_FUNCTION = new Function<CustomerStatus, String>() {
		@Override
		public String apply(CustomerStatus status) {
			return status.getName();
		}
	};

	private static final Map<String, CustomerStatus> NAME_LOOKUP = Maps.uniqueIndex(Arrays.asList(CustomerStatus.class.getEnumConstants()), NAME_FUNCTION);

}
