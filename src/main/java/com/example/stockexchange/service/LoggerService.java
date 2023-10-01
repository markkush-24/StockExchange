package com.example.stockexchange.service;

import com.example.stockexchange.dto.StockDataDto;
import com.example.stockexchange.entity.StockData;
import com.example.stockexchange.repository.StockDataRepository;
import com.example.stockexchange.dao.LoggerDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
@RequiredArgsConstructor
public class LoggerService {

    private final StockDataRepository stockDataRepository;
    private final LoggerDAO loggerDAO;

    public void getTop5MostExpensiveStock() {
        Flux<StockDataDto> list = stockDataRepository.getTop5MostExpensiveStock();
        loggerDAO.logStockData(list);
    }

    public void getPriceDifferenceBetweenShares() {
        Flux<StockData> list = stockDataRepository.priceDifferenceBetweenShares();
        loggerDAO.logDifferenceLastPrice(list);
    }
}
