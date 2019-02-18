package com.wisehub.platform.data.model.dao.impl;

import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.datastax.driver.core.querybuilder.Clause;
import com.wisehub.platform.data.model.FinancialByProduct;
import com.wisehub.platform.data.model.dao.DTOParameter;
import com.wisehub.platform.data.model.dao.FinancialByProductRepository;

@Repository
public class FinancialByProductRepositoryImpl extends AbstractCrudRepository<FinancialByProduct, UUID> implements FinancialByProductRepository {

	private static Logger logger = LoggerFactory.getLogger(FinancialByProductRepositoryImpl.class.getName());

	private static final String TABLE_NAME = "financials_by_product";

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
	public Class<FinancialByProduct> getEntityClass() {
		return FinancialByProduct.class;
	}

	public Clause buildWhere(DTOParameter parameter) {
		List<String> names = new ArrayList<>();
		List<Object> values = new ArrayList<>();

		if (parameter.getProductId() != null && !StringUtils.isEmpty(parameter.getProductId().toString())) {
			names.add(KEY_COLUMN);
			values.add(parameter.getProductId());
			if (parameter.getCreatedAt() != null && !StringUtils.isEmpty(parameter.getCreatedAt().toString())) {
				names.add("created_at");
				values.add(parameter.getCreatedAt());

			}
		}

		return values.isEmpty() ? null : (values.size() > 1 ? eq(names, values) : eq(names.get(0), values.get(0)));
	}

}