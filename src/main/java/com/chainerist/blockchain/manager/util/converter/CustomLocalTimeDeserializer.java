/**
 *
 */
package com.chainerist.blockchain.manager.util.converter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalTime;


public class CustomLocalTimeDeserializer extends JsonDeserializer<LocalTime> {

	@Override
	public LocalTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		try {
			String value = ConverterUtil.decode(p.getValueAsString());
			return LocalTime.parse(value, DateTimeFormatters.TIME_FORMATTER);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
