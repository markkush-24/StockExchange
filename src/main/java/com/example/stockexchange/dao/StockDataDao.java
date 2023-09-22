package com.example.stockexchange.dao;

import com.example.stockexchange.entity.StockData;
import com.example.stockexchange.repository.StockDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class StockDataDao {

    private final StockDataRepository stockDataRepository;

    public Mono<Void> save(StockData stockData) {
        return stockDataRepository.save(stockData).then();
    }
}
