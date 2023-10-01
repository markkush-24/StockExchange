package com.example.stockexchange.repository;

import com.example.stockexchange.entity.PreviousVersionStockData;
import com.example.stockexchange.entity.ShortCompany;
import com.example.stockexchange.entity.StockData;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CustomRepository {
    Mono<Void> saveCompanies(List<ShortCompany> companies);

    Flux<StockData> saveStocks(Flux<StockData> stocks);

    Flux<PreviousVersionStockData> savePreviousVersionStockData(Flux<StockData> stockData);
}
