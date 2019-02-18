package com.wisehub.platform.core.orm.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name = "fin_inst")
@NamedQueries({
		@NamedQuery(name = "com.wisehub.platform.core.orm.entity.FinancialInstitution.findCredentialsById", query = "SELECT a FROM FinancialInstitution a"
				+ " WHERE a.fin_inst_id = :finInstId") })

public class FinancialInstitution {

	private static Logger logger = LoggerFactory.getLogger(FinancialInstitution.class.getName());

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "fin_inst_id", nullable = false)
	private Integer fin_inst_id;

	@Column(name = "fin_inst_name")
	private String finInstName;

	@Column(name = "fin_code")
	private String finCode;

	@Column(name = "created")
	private DateTime created;

	@Column(name = "last_mod")
	private DateTime lastMod;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "financialInstitution")
	private Address address;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "financialInstitution")
	private Set<Branch> branches = new HashSet<Branch>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "user_fin_institution", joinColumns = {
			@JoinColumn(name = "user_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "fin_inst_id", nullable = false, updatable = false) })
	private Set<User> users = new HashSet<User>();

	public FinancialInstitution() {
	}

	public FinancialInstitution(String finInstName, Address address) {
		this.finInstName = finInstName;
		this.address = address;
	}

	public Integer getId() {
		return fin_inst_id;
	}

	public String getFinInstName() {
		return finInstName;
	}

	public void setFinInstName(String finInstName) {
		this.finInstName = finInstName;
	}

	public String getFinCode() {
		return finCode;
	}

	public void setFinCode(String finCode) {
		this.finCode = finCode;
	}

	public Set<User> getUsers() {
		return users;
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

	@Override
	public String toString() {
		return getFinInstName();
	}
}
