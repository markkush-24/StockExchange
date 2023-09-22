package com.example.stockexchange.dao;

import com.example.stockexchange.dto.StockDataDto;
import com.example.stockexchange.repository.StockDataRepository;
import com.example.stockexchange.utils.QuoteLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
@RequiredArgsConstructor
public class QuoteDAO {

    private final StockDataRepository stockDataRepository;
    private final QuoteLogger quoteLogger;

    public void getTop5MostExpensiveStock() {
        Flux<StockDataDto> list = stockDataRepository.getTop5MostExpensiveStock();
        quoteLogger.logStockData(list);
    }

    public void getPriceDifferenceBetweenShares() {
        Flux<Object[]> list = stockDataRepository.priceDifferenceBetweenShares();
//        quoteLogger.logPriceDifference(list);
    }
}
