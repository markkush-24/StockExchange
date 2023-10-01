package com.example.stockexchange.dao;

import com.example.stockexchange.dto.StockDataDto;
import com.example.stockexchange.entity.DifferenceLastPrice;
import com.example.stockexchange.entity.StockData;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class LoggerDAO {
    private static final Logger logger = Logger.getLogger(LoggerDAO.class);

    public void logStockData(Flux<StockDataDto> stockDataFlux) {
        stockDataFlux.subscribe(data -> {
            logger.info("Company: " + data.getCompanyName());
            logger.info("Volume: " + data.getPreviousVolume());
            logger.info("");
        });
    }

    Flux<DifferenceLastPrice> mapToDifferenceLastPrice(Flux<StockData> flux) {
        return flux.map(objects -> {
            DifferenceLastPrice differenceLastPrice = new DifferenceLastPrice();
            differenceLastPrice.setCompanyName(objects.getCompanyName());
            differenceLastPrice.setDifference(objects.getLatestPrice());
            return differenceLastPrice;
        });
    }

    public void logDifferenceLastPrice(Flux<StockData> flux) {
        Flux<DifferenceLastPrice> differenceLastPriceFlux = mapToDifferenceLastPrice(flux);
        differenceLastPriceFlux.subscribe(data -> {
            logger.info("Company: " + data.getCompanyName());
            logger.info("Volume: " + data.getDifference());
            logger.info("");
        });
    }
}
