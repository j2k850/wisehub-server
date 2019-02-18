package com.wisehub.platform.core.orm.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

import org.hibernate.annotations.BatchSize;

import org.joda.time.DateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name = "users")
public class User {

	private static Logger logger = LoggerFactory.getLogger(User.class.getName());

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id", nullable = false)
	private Integer id;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "middle_name", nullable = true)
	private String middleName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "nickname", nullable = false)
	private String nickName;

	@Column(name = "user_name", nullable = false)
	private String userName;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "created")
	private DateTime created;

	@Column(name = "last_mod")
	private DateTime lastMod;
/*
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
	private Address address;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
	private Account account;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	@BatchSize(size = 200)
	private Set<Txaction> txActions = new HashSet<Txaction>();
	*/

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "users")
	private Set<FinancialInstitution> financialInstitutions = new HashSet<FinancialInstitution>();

	public User() {
	}
/*
	public User(Address address, String fn, String ln, String email, String userName, String password) {
		this.address = address;
		this.firstName = fn;
		this.lastName = ln;
		this.email = email;
		this.password = password;
		this.userName = userName;
	}
	*/

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public Set<FinancialInstitution> getFinancialInstitutions() {
		return financialInstitutions;
	}

	public void setFinancialInstitutions(Set<FinancialInstitution> financialInstitutions) {
		this.financialInstitutions = financialInstitutions;
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
		return getFirstName() + " " + getLastName();
	}
}
