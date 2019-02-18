package com.wisehub.platform.api.email;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Strings;

public class Recipient {
	public enum Type {
		TO, CC, BCC
	}

	private String address;
	private String name;
	private Type type;
	private Map<String, String> substitutions;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Map<String, String> getSubstitutions() {
		return substitutions;
	}

	public void setSubstitutions(Map<String, String> substitutions) {
		this.substitutions = substitutions;
	}

	public void addSubstitution(String name, String value) {
		checkNotNull(name, "Can't have a null Substitute name");

		if (substitutions == null) {
			substitutions = new HashMap<String,String>();
		}

		substitutions.put(name, Strings.nullToEmpty(value));
	}

	public void addSubstitution(String name, int value) {
		addSubstitution(name, Integer.toString(value));
	}

	public void addSubstitution(String name, long value) {
		addSubstitution(name, Long.toString(value));
	}

	public void addSubstitution(String name, float value) {
		addSubstitution(name, Float.toString(value));
	}

	public void addSubstitution(String name, double value) {
		addSubstitution(name, Double.toString(value));
	}

	public void addSubstitution(String name, Object value) {
		if (value == null) {
			addSubstitution(name, "");
		} else {
			addSubstitution(name, value.toString());
		}
	}
}
