/**
 *
 */
package com.chainerist.blockchain.manager.util.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalTime;


public class CustomLocalTimeSerializer extends JsonSerializer<LocalTime> {

	@Override
	public void serialize(LocalTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		try {
			var s = value.format(DateTimeFormatters.TIME_FORMATTER);
			gen.writeString(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
