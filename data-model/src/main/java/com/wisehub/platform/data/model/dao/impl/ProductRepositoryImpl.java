package com.wisehub.platform.data.model.dao.impl;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.wisehub.platform.data.model.Product;
import com.wisehub.platform.data.model.dao.ProductRepository;

@Repository
public class ProductRepositoryImpl extends AbstractVersionCrudRepository<Product, UUID> implements ProductRepository {

	private static Logger logger = LoggerFactory.getLogger(ProductRepositoryImpl.class.getName());

	private static final String TABLE_NAME = "products";

	private static final String KEY_COLUMN = "product_id";

	@Override
	public String getTableName() {
		return TABLE_NAME;
	}

	@Override
	public String getKeyName() {
		return KEY_COLUMN;
	}

	@Override
	public Class<Product> getEntityClass() {
		return Product.class;
	}

}