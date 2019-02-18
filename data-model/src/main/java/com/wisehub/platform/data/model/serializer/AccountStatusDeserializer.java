package com.wisehub.platform.data.model.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.wisehub.platform.data.model.AccountStatus;

public class AccountStatusDeserializer extends StdDeserializer<AccountStatus> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AccountStatusDeserializer() {
		this(AccountStatus.class);
	}

	public AccountStatusDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public AccountStatus deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		
		if (jp.getCurrentToken() == JsonToken.START_OBJECT) {
			while (jp.getCurrentToken() != JsonToken.END_OBJECT) {
				JsonToken token = jp.nextToken();
				if (jp.getCurrentToken() == JsonToken.FIELD_NAME && "status".equals(jp.getValueAsString().toLowerCase())) {
					token = jp.nextToken();
					return AccountStatus.byName(jp.getValueAsString().toUpperCase());
				}
			}
		}
		
		return AccountStatus.ACTIVE;		

	}

}