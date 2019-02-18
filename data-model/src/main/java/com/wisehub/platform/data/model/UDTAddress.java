package com.wisehub.platform.data.model;

import java.util.Set;

import com.datastax.driver.mapping.annotations.Field;
import com.datastax.driver.mapping.annotations.UDT;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "address")
@UDT(name = "address", keyspace = "platform")
public class UDTAddress {

	@ApiModelProperty(value ="The street of address", dataType = "String")
	@Field
	private String street;

	@ApiModelProperty(value ="The city of address", dataType = "String")
	@Field
	private String city;

	@ApiModelProperty(value ="The postal code of address", dataType = "String", name = "postal_code")
	@Field(name = "postal_code")
	private String postalCode;

	@ApiModelProperty(value ="The region of address", dataType = "String")
	@Field
	private String region;

	@ApiModelProperty(value ="The country of address", dataType = "String")
	@Field
	private String country;

	@ApiModelProperty(value ="The phones of address", dataType = "Set", name = "phones")
	@Field
	private Set<String> phones;

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Set<String> getPhones() {
		return phones;
	}

	public void setPhones(Set<String> phones) {
		this.phones = phones;
	}

	@Override
	public String toString() {
		return "UDTAddress [" + (street != null ? "street=" + street + ", " : "") + (city != null ? "city=" + city + ", " : "")
				+ (postalCode != null ? "postalCode=" + postalCode + ", " : "") + (region != null ? "region=" + region + ", " : "")
				+ (country != null ? "country=" + country + ", " : "") + (phones != null ? "phones=" + phones : "") + "]";
	}

	
}
