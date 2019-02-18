package com.wisehub.platform.api.domain.model;

import java.util.Arrays;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.base.Function;
import com.google.common.collect.Maps;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum NotificationStatus {

	PENDING(0, "Pending"), 
	RESPONDED(1, "Responded");

	final private int id;
	final private String name;

	private NotificationStatus(int value, String name) {
		this.name = name;
		this.id = value;
	}

	public int getId() {
		return id;
	}

	public static NotificationStatus byValue(int value) {
		return NAME_LOOKUP.get(value);
	}

	public String getName() {
		return name;
	}

	public static NotificationStatus byName(String name) {
		return NAME_LOOKUP.get(name);
	}

	private static final Function<NotificationStatus, String> NAME_FUNCTION = new Function<NotificationStatus, String>() {
		@Override
		public String apply(NotificationStatus status) {
			return status.getName();
		}
	};

	private static final Map<String, NotificationStatus> NAME_LOOKUP = Maps.uniqueIndex(Arrays.asList(NotificationStatus.class.getEnumConstants()),
			NAME_FUNCTION);

}
