package com.example.stockexchange.utils;

import com.example.stockexchange.entity.PreviousVersionStockData;
import com.example.stockexchange.entity.StockData;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Converter {
    public static PreviousVersionStockData convert(StockData stockData) {
        PreviousVersionStockData previousVersionStockData = new PreviousVersionStockData();
        previousVersionStockData.setId(stockData.getId());
        previousVersionStockData.setChange(stockData.getChange());
        previousVersionStockData.setCompanyName(stockData.getCompanyName());
        previousVersionStockData.setLatestPrice(stockData.getLatestPrice());
        previousVersionStockData.setPreviousVolume(stockData.getPreviousVolume());
        previousVersionStockData.setSymbol(stockData.getSymbol());
        previousVersionStockData.setVolume(stockData.getVolume());
        return previousVersionStockData;
    }
}