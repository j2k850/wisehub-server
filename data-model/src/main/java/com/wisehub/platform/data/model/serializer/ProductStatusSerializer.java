package com.wisehub.platform.data.model.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.wisehub.platform.data.model.ProductStatus;

public class ProductStatusSerializer extends StdSerializer<ProductStatus> {

	public ProductStatusSerializer() {
		super(ProductStatus.class);
	}

	protected ProductStatusSerializer(Class<ProductStatus> t) {
		super(t);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void serialize(ProductStatus type, JsonGenerator generator, SerializerProvider provider) throws IOException, JsonProcessingException {
		generator.writeStartObject();
		generator.writeFieldName("status");
		generator.writeString(type.getDescription());
		generator.writeEndObject();
	}

}