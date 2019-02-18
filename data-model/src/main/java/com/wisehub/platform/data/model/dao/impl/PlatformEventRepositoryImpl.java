package com.wisehub.platform.data.model.dao.impl;

import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.datastax.driver.core.querybuilder.Clause;
import com.wisehub.platform.data.model.PlatformEvent;
import com.wisehub.platform.data.model.dao.DTOParameter;
import com.wisehub.platform.data.model.dao.PlatformEventRepository;
import com.wisehub.platform.data.model.util.TimeUUID;

@Repository
public class PlatformEventRepositoryImpl extends AbstractCrudRepository<PlatformEvent, UUID> implements PlatformEventRepository {

	private static Logger logger = LoggerFactory.getLogger(PlatformEventRepositoryImpl.class.getName());

	private static final String TABLE_NAME = "platform_event";

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
	public Class<PlatformEvent> getEntityClass() {
		return PlatformEvent.class;
	}
	
	
	public Clause buildWhere(DTOParameter parameter) {
		List<String> names = new ArrayList<>();
		List<Object> values = new ArrayList<>();

		if (parameter.getProductId() != null && !StringUtils.isEmpty(parameter.getProductId().toString())) {
			names.add(KEY_COLUMN);
			values.add(parameter.getUserId());

			if (parameter.getUserId() != null && !StringUtils.isEmpty(parameter.getUserId().toString())) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date eventTimestamp;
				try {
					eventTimestamp = sdf.parse(parameter.getCreatedAt());
					UUID queryUUID = TimeUUID.buildTimeUUID(eventTimestamp.getTime());
					names.add("event_timestamp");
					values.add(queryUUID);
					
					if (parameter.getCreatedAt() != null && !StringUtils.isEmpty(parameter.getCreatedAt().toString())) {
						names.add("event");
						values.add(parameter.getEvent());
						
					}
				
				} catch (ParseException e) {
					e.printStackTrace();
				}
				

			}

		}
		return values.isEmpty() ? null : (values.size() > 1 ? eq(names, values) : eq(names.get(0), values.get(0)));
	}


}