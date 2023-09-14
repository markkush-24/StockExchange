package com.example.stockexchange.dao;

import com.example.stockexchange.entity.StockData;
import com.example.stockexchange.repository.StockDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StockDataDao {

    private final StockDataRepository stockDataRepository;

    public void save(StockData stockData) {
        stockDataRepository.save(stockData);
    }
}
