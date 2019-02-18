package com.wisehub.platform.core.orm.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name = "branch")
public class Branch {

	private static Logger logger = LoggerFactory.getLogger(Branch.class.getName());

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "branch_id", nullable = false)
	private Integer id;

	@Column(name = "branch_name")
	private String branchName;

	@Column(name = "branch_mgr")
	private String branchMgr;

	@Column(name = "created")
	private DateTime created;

	@Column(name = "last_mod")
	private DateTime lastMod;

	@ManyToOne(cascade = CascadeType.ALL)
	private FinancialInstitution financialInstitution;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "branch")
	private Address address;

	@OneToOne
	@PrimaryKeyJoinColumn
	private Account account;

	public Branch() {
	}

	public Branch(String branchName, String branchMgr, FinancialInstitution financialInstitution, Address address) {
		this.branchName = branchName;
		this.branchMgr = branchMgr;
		this.financialInstitution = financialInstitution;
		this.address = address;
	}

	public Integer getId() {
		return id;
	}

	public String getBranchName() {
		return branchName;
	}

	public String getBranchMgr() {
		return branchMgr;
	}

	public FinancialInstitution getFinancialInstitution() {
		return financialInstitution;
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
		return getBranchName();
	}
}
