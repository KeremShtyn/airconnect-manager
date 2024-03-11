package com.chainerist.blockchain.manager.util.converter;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class ConverterUtil {

    public static String decode(String value) {
        try {
            return URLDecoder.decode(value, StandardCharsets.UTF_8.name());
        } catch (Exception e) {
        }
        return value;
    }
}
