package com.example.stockexchange.utils;

import lombok.experimental.UtilityClass;
import org.apache.log4j.Logger;

import java.util.List;

@UtilityClass
public class QuoteLogger {
    private static final Logger logger = Logger.getLogger(QuoteLogger.class);

    public static <T> void loggerStock(List<T> list) {
        for (T obj : list) {
            logger.info(obj.toString());
        }
    }
}
