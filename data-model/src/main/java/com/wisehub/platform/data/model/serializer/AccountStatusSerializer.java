package com.wisehub.platform.data.model.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.wisehub.platform.data.model.AccountStatus;

public class AccountStatusSerializer extends StdSerializer<AccountStatus> {

	public AccountStatusSerializer() {
		super(AccountStatus.class);
	}

	protected AccountStatusSerializer(Class<AccountStatus> t) {
		super(t);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void serialize(AccountStatus type, JsonGenerator generator, SerializerProvider provider) throws IOException, JsonProcessingException {
		generator.writeStartObject();
		generator.writeFieldName("status");
		generator.writeString(type.getDescription());
		generator.writeFieldName("id");
		generator.writeNumber(type.getValue());
		generator.writeEndObject();
	}

}