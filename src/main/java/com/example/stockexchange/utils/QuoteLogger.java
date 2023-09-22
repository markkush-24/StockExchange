package com.example.stockexchange.utils;

import com.example.stockexchange.dto.StockDataDto;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class QuoteLogger {
    private static final Logger logger = Logger.getLogger(QuoteLogger.class);

    public void logStockData(Flux<StockDataDto> stockDataFlux) {
        stockDataFlux.subscribe(data -> {
            logger.info("Company: " + data.getCompanyName());
            logger.info("Volume: " + data.getPreviousVolume());
            logger.info("");
        });
    }
}
