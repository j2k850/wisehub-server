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
import com.wisehub.platform.data.model.UserFinancialInstitution;
import com.wisehub.platform.data.model.dao.DTOParameter;
import com.wisehub.platform.data.model.dao.UserFinancialInstitutionRepository;

@Repository
public class UserFinancialInstitutionRepositoryImpl extends AbstractCrudRepository<UserFinancialInstitution, UUID>
		implements UserFinancialInstitutionRepository {

	private static Logger logger = LoggerFactory.getLogger(UserFinancialInstitutionRepositoryImpl.class.getName());

	private static final String TABLE_NAME = "user_fin_insts";

	private static final String KEY_COLUMN = "user_id";

	@Override
	public String getTableName() {
		return TABLE_NAME;
	}

	@Override
	public String getKeyName() {
		return KEY_COLUMN;
	}

	@Override
	public Class<UserFinancialInstitution> getEntityClass() {
		return UserFinancialInstitution.class;
	}

	public Clause buildWhere(DTOParameter parameter) {
		List<String> names = new ArrayList<>();
		List<Object> values = new ArrayList<>();

		if (parameter.getUserId() != null && !StringUtils.isEmpty(parameter.getUserId().toString())) {
			names.add(KEY_COLUMN);
			values.add(parameter.getUserId());

			if (parameter.getCreatedAt() != null && !StringUtils.isEmpty(parameter.getCreatedAt().toString())) {
				names.add("created_at");
				values.add(parameter.getCreatedAt());

				if (parameter.getFinancialInstitutionId() != null && !StringUtils.isEmpty(parameter.getFinancialInstitutionId().toString())) {
					names.add("fin_inst_id");
					values.add(parameter.getFinancialInstitutionId());
				}
			}

		}

		return values.isEmpty() ? null : (values.size() > 1 ? eq(names, values) : eq(names.get(0), values.get(0)));
	}

}