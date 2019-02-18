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
import com.wisehub.platform.data.model.CorrespondenceByUser;
import com.wisehub.platform.data.model.dao.CorrespondenceByUserRepository;
import com.wisehub.platform.data.model.dao.DTOParameter;

@Repository
public class CorrespondenceByUserRepositoryImpl extends AbstractCrudRepository<CorrespondenceByUser, UUID> implements CorrespondenceByUserRepository {

	private static Logger logger = LoggerFactory.getLogger(CorrespondenceByUserRepositoryImpl.class.getName());

	private static final String TABLE_NAME = "correspondence_by_user";

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
	public Class<CorrespondenceByUser> getEntityClass() {
		return CorrespondenceByUser.class;
	}

	public Clause buildWhere(DTOParameter parameter) {
		List<String> names = new ArrayList<>();
		List<Object> values = new ArrayList<>();

		if (parameter.getAccountId() != null && !StringUtils.isEmpty(parameter.getAccountId().toString())) {
			names.add(KEY_COLUMN);
			values.add(parameter.getAccountId());

			if (parameter.getCreatedAt() != null && !StringUtils.isEmpty(parameter.getCreatedAt().toString())) {
				names.add("created_at");
				values.add(parameter.getCreatedAt());

				if (parameter.getEvent() != null && !StringUtils.isEmpty(parameter.getEvent().toString())) {
					names.add("event");
					values.add(parameter.getEvent());
				}
			}

		}

		return values.isEmpty() ? null : (values.size() > 1 ? eq(names, values) : eq(names.get(0), values.get(0)));
	}

}