package com.chainerist.blockchain.manager;

import com.chainerist.blockchain.manager.util.converter.*;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@SpringBootApplication
public class BlockchainManagerApplication {

    @Bean(name = "SIGN_OBJECT_MAPPER")
    @Primary
    public ObjectMapper objectMapper() {
        var factory = new JsonFactory();
        factory.enable(JsonParser.Feature.ALLOW_SINGLE_QUOTES);
        factory.enable(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES);
        factory.enable(JsonReadFeature.ALLOW_MISSING_VALUES.mappedFeature());

        var objectMapper = new ObjectMapper(factory);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        objectMapper.setVisibility(PropertyAccessor.CREATOR, JsonAutoDetect.Visibility.ANY);
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        var javaTimeModule = new SimpleModule();
        javaTimeModule.addSerializer(LocalDate.class, new CustomLocalDateSerializer());
        javaTimeModule.addDeserializer(LocalDate.class, new CustomLocalDateDeserializer());
        javaTimeModule.addSerializer(LocalDateTime.class, new CustomLocalDateTimeSerializer());
        javaTimeModule.addDeserializer(LocalDateTime.class, new CustomLocalDateTimeDeserializer());
        javaTimeModule.addSerializer(LocalTime.class, new CustomLocalTimeSerializer());
        javaTimeModule.addDeserializer(LocalTime.class, new CustomLocalTimeDeserializer());
        objectMapper.registerModule(javaTimeModule);
        return objectMapper;
    }





    public static void main(String[] args) {
        SpringApplication.run(BlockchainManagerApplication.class, args);
    }

}
