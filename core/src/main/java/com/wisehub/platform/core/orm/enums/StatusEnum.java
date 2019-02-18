package com.wisehub.platform.core.orm.enums;

import java.util.EnumSet;
import java.util.Set;

public enum StatusEnum {
	active, inactive, deleted;

	public static final String SQL_TYPE = "enum('active','inactive','deleted')";

	public static Set<StatusEnum> activeList() {
		return EnumSet.of(StatusEnum.active);
	}

	public static Set<StatusEnum> parseArray(String[] statusArray) {
		Set<StatusEnum> statusList = EnumSet.noneOf(StatusEnum.class);
		for (String status : statusArray) {
			statusList.add(StatusEnum.valueOf(status));
		}
		return statusList;
	}

	public static Set<StatusEnum> notDeleted() {
		return EnumSet.complementOf(EnumSet.of(StatusEnum.deleted));
	}
}