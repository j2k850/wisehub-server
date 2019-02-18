package com.wisehub.platform.core.orm.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name = "product")
public class Product {

	private static Logger logger = LoggerFactory.getLogger(Product.class.getName());

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "product_id", nullable = false)
	private Integer id;

	@Column(name = "product_name")
	private String productName;

	@Column(name = "product_description")
	private String productDescription;

	@Column(name = "min_crit")
	private String minCrit;

	@Column(name = "max_crit")
	private String maxCrit;

	@Column(name = "created")
	private DateTime created;

	@Column(name = "last_mod")
	private DateTime lastMod;

	@ManyToOne(cascade = CascadeType.ALL)
	private FinancialInstitution financialInstitution;

	public Product() {
	}

	public Product(String productName, String productDescription, FinancialInstitution financialInstitution) {
		this.productName = productName;
		this.productDescription = productDescription;
		this.financialInstitution = financialInstitution;
	}

	public Integer getId() {
		return id;
	}

	public String getProductName() {
		return productName;
	}

	public String getProductDescription() {
		return productDescription;
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

}