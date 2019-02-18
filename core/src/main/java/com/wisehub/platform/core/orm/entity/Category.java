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
@Table(name = "category")
public class Category {

	private static Logger logger = LoggerFactory.getLogger(Category.class.getName());

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "category_id", nullable = false)
	private Integer id;

	@Column(name = "category_name")
	private String categoryName;

	@Column(name = "created")
	private DateTime created;

	@Column(name = "last_mod")
	private DateTime lastMod;

	@ManyToOne(cascade = CascadeType.ALL)
	private Txaction txaction;

	public Category() {
	}

	public Category(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getId() {
		return id;
	}

	public String getCategoryName() {
		return categoryName;
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
		return getCategoryName();
	}
}
