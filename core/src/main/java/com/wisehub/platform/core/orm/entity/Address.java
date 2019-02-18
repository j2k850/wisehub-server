package com.wisehub.platform.core.orm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name = "address")
public class Address {

	private static Logger logger = LoggerFactory.getLogger(Address.class.getName());

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "address_id", nullable = false)
	private Integer id;

	@Column(name = "address")
	private String address;

	@Column(name = "address2")
	private String address2;

	@Column(name = "phone")
	private String phone;

	@Column(name = "region")
	private String region;

	@Column(name = "postal_code")
	private String postalCode;

	@Column(name = "city")
	private String city;

	@Column(name = "created")
	private DateTime created;

	@Column(name = "last_mod")
	private DateTime lastMod;

	@OneToOne
	@PrimaryKeyJoinColumn
	private FinancialInstitution financialInstitution;

	@OneToOne
	@PrimaryKeyJoinColumn
	private Branch branch;

	@OneToOne
	@PrimaryKeyJoinColumn
	private User user;

	public Address() {
	}

	public Address(String address, String phone, String city) {
		this.address = address;
		this.phone = phone;
		this.city = city;
	}

	public Integer getId() {
		return id;
	}

	public String getAddress() {
		return address;
	}

	public DateTime getCreated() {
		return created;
	}

	public void setCreated(DateTime created) {
		this.created = created;
	}

	public DateTime getLastMod() {
		return lastMod;
	}

	public void setLastMod(DateTime lastMod) {
		this.lastMod = lastMod;
	}

	public String getAddress2() {
		return this.address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	@Override
	public String toString() {
		return getAddress();
	}
}
