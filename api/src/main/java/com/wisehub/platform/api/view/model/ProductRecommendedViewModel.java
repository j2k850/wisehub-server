package com.wisehub.platform.api.view.model;

import java.util.Arrays;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.base.Function;
import com.google.common.collect.Maps;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ProductRecommendedViewModel {

	SAVINGS(0, "Savings"), 
	LOANS(1, "Loans"), 
	INSURANCE(2, "Insurance"), 
	INVESTMENT(3, "Investment");

	final private int id;
	final private String name;

	private ProductRecommendedViewModel(int value, String name) {
		this.name = name;
		this.id = value;
	}

	public int getId() {
		return id;
	}

	public static ProductRecommendedViewModel byValue(int value) {
		return NAME_LOOKUP.get(value);
	}

	public String getName() {
		return name;
	}

	public static ProductRecommendedViewModel byName(String name) {
		return NAME_LOOKUP.get(name);
	}

	private static final Function<ProductRecommendedViewModel, String> NAME_FUNCTION = new Function<ProductRecommendedViewModel, String>() {
		@Override
		public String apply(ProductRecommendedViewModel status) {
			return status.getName();
		}
	};

	private static final Map<String, ProductRecommendedViewModel> NAME_LOOKUP = Maps
			.uniqueIndex(Arrays.asList(ProductRecommendedViewModel.class.getEnumConstants()), NAME_FUNCTION);

}
