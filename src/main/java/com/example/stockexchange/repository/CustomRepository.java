package com.example.stockexchange.repository;

import com.example.stockexchange.entity.ShortCompany;
import com.example.stockexchange.entity.StockData;
import reactor.core.publisher.Flux;

import java.util.List;

public interface CustomRepository {
    void saveCompanies(List<ShortCompany> companies);

    Flux<StockData> saveStocks(Flux<StockData> stocks);
}
