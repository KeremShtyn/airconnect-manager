/**
 *
 */
package com.chainerist.blockchain.manager.util.converter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDate;


public class CustomLocalDateDeserializer extends JsonDeserializer<LocalDate> {

	@Override
	public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		String value = ConverterUtil.decode(p.getValueAsString());
		return LocalDate.parse(value, DateTimeFormatters.DATE_FORMATTER);
	}

}
