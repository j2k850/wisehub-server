package com.wisehub.platform.core.orm.dao.impl;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.wisehub.platform.core.orm.dao.ProductDAO;
import com.wisehub.platform.core.orm.entity.Product;

/**
 * A data access object for retrieving and storing {@link Product} instances.
 * 
 * @author jkwofie
 */
public class ProductDAOImpl implements ProductDAO {

	private static Logger logger = LoggerFactory.getLogger(ProductDAOImpl.class.getName());

	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void save(Product p) {
		// TODO Auto-generated method stub

	}
}