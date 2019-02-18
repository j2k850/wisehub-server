package com.wisehub.platform.data.model;

import java.util.Arrays;
import java.util.Map;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.wisehub.platform.data.model.serializer.ProductStatusDeserializer;
import com.wisehub.platform.data.model.serializer.ProductStatusSerializer;

/**
 * The status of an {@link Product}.
 * 
 * @author jkwofie
 *
 */
@JsonDeserialize(using = ProductStatusDeserializer.class)
@JsonSerialize(using = ProductStatusSerializer.class)
public enum ProductStatus {


	RECOMMENDED(0,"Recommended"),

	MATCHED(1,"Matched"),
	
	INACTIVE(2,"INACTIVE"),
	;

	final private int value;
	final private String description;

	private ProductStatus(int value, String description) {
		this.description  = description;
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}

	public static ProductStatus byValue(int value) {
		return NAME_LOOKUP.get(value);
	}
	
	public String getDescription() {
		return description;
	}
	
	public static ProductStatus byName(String name) {
		return NAME_LOOKUP.get(name.toUpperCase());
	}
	
	
	private static final Function<ProductStatus, String> NAME_FUNCTION =
		new Function<ProductStatus, String>(){
			@Override
			public String apply(ProductStatus status) {
				return status.toString();
			}
		};

	private static final Map<String, ProductStatus> NAME_LOOKUP =
		Maps.uniqueIndex(
			Arrays.asList(ProductStatus.class.getEnumConstants()),
			NAME_FUNCTION
		);

}
