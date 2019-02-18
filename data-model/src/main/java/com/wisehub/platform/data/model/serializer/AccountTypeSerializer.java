package com.wisehub.platform.data.model.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.wisehub.platform.data.model.AccountType;

public class AccountTypeSerializer extends StdSerializer<AccountType> {

	public AccountTypeSerializer() {
		super(AccountType.class);
	}

	protected AccountTypeSerializer(Class<AccountType> t) {
		super(t);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void serialize(AccountType type, JsonGenerator generator, SerializerProvider provider) throws IOException, JsonProcessingException {
		generator.writeStartObject();
		generator.writeFieldName("name");
		generator.writeString(type.name());

		generator.writeFieldName("has_balance");
		generator.writeBoolean(type.hasBalance());
		generator.writeFieldName("has_uploads");
		generator.writeBoolean(type.hasUploads());
		generator.writeFieldName("has_continuous_balance");
		generator.writeBoolean(type.hasContinuousBalance());
		generator.writeEndObject();
	}

}