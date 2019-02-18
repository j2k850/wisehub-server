package com.wisehub.platform.data.model;

import java.util.Arrays;
import java.util.Map;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.wisehub.platform.data.model.serializer.AccountStatusDeserializer;
import com.wisehub.platform.data.model.serializer.AccountStatusSerializer;

/**
 * The status of an {@link Account}.
 * 
 * @author jkwofie
 *
 */
@JsonDeserialize(using = AccountStatusDeserializer.class)
@JsonSerialize(using = AccountStatusSerializer.class)
public enum AccountStatus {

	/**
	 * A regular, active account.
	 */
	ACTIVE(0,"ACTIVE"),

	/**
	 * An account which has been deleted.
	 */
	DELETED(1,"DELETED"),

	/**
	 * An account which has been locked.
	 */
	LOCKED(2,"LOCKED"),

	/**
	 * An account which has been archived.
	 */
	ARCHIVED(3,"ARCHIVED"),

	/**
	 * An account which has been pending.
	 */
	PENDING(4,"PENDING"),
	/**
	 * An account which has imports disabled.
	 */
	DISABLED(5,"DISABLED");

	final private int value;
	final private String description;

	private AccountStatus(int value, String description) {
		this.description  = description;
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}

	public static AccountStatus byValue(int value) {
		return NAME_LOOKUP.get(value);
	}
	
	public String getDescription() {
		return description;
	}
	
	public static AccountStatus byName(String name) {
		return NAME_LOOKUP.get(name.toUpperCase());
	}
	
	
	private static final Function<AccountStatus, String> NAME_FUNCTION =
		new Function<AccountStatus, String>(){
			@Override
			public String apply(AccountStatus status) {
				return status.toString();
			}
		};

	private static final Map<String, AccountStatus> NAME_LOOKUP =
		Maps.uniqueIndex(
			Arrays.asList(AccountStatus.class.getEnumConstants()),
			NAME_FUNCTION
		);

}
