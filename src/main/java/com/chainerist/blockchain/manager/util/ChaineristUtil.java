package com.chainerist.blockchain.manager.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ChaineristUtil {

    private ChaineristUtil(){}

    public static boolean isNullOrEmptyObject(Object obj) {
        return (obj == null || obj == "");
    }

    public static final String formatLocalDateTimeAsString(LocalDateTime localDateTime, String dateFormat){
        var formatter = DateTimeFormatter.ofPattern(dateFormat);
        return localDateTime.format(formatter);
    }

}
