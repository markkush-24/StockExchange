package com.example.stockexchange.utils;

import com.example.stockexchange.entity.PreviousVersionStockData;
import com.example.stockexchange.entity.StockData;

public class Converter {
    public static PreviousVersionStockData convert(StockData stockData) {
        PreviousVersionStockData previousVersionStockData = new PreviousVersionStockData();
        previousVersionStockData.setId(stockData.getId());
        previousVersionStockData.setAvgTotalVolume(stockData.getAvgTotalVolume());
        previousVersionStockData.setCalculationPrice(stockData.getCalculationPrice());
        previousVersionStockData.setChange(stockData.getChange());
        previousVersionStockData.setChangePercent(stockData.getChangePercent());
        previousVersionStockData.setClose(stockData.getClose());
        previousVersionStockData.setCloseSource(stockData.getCloseSource());
        previousVersionStockData.setCloseTime(stockData.getCloseTime());
        previousVersionStockData.setCompanyName(stockData.getCompanyName());
        previousVersionStockData.setCurrency(stockData.getCurrency());
        previousVersionStockData.setDelayedPrice(stockData.getDelayedPrice());
        previousVersionStockData.setDelayedPriceTime(stockData.getDelayedPriceTime());
        previousVersionStockData.setExtendedChange(stockData.getExtendedChange());
        previousVersionStockData.setExtendedChangePercent(stockData.getExtendedChangePercent());
        previousVersionStockData.setExtendedPrice(stockData.getExtendedPrice());
        previousVersionStockData.setExtendedPriceTime(stockData.getExtendedPriceTime());
        previousVersionStockData.setHigh(stockData.getHigh());
        previousVersionStockData.setHighSource(stockData.getHighSource());
        previousVersionStockData.setHighTime(stockData.getHighTime());
        previousVersionStockData.setIexAskPrice(stockData.getIexAskPrice());
        previousVersionStockData.setIexAskSize(stockData.getIexAskSize());
        previousVersionStockData.setIexBidPrice(stockData.getIexBidPrice());
        previousVersionStockData.setIexBidSize(stockData.getIexBidSize());
        previousVersionStockData.setIexClose(stockData.getIexClose());
        previousVersionStockData.setIexCloseTime(stockData.getIexCloseTime());
        previousVersionStockData.setIexLastUpdated(stockData.getIexLastUpdated());
        previousVersionStockData.setIexMarketPercent(stockData.getIexMarketPercent());
        previousVersionStockData.setIexOpen(stockData.getIexOpen());
        previousVersionStockData.setIexOpenTime(stockData.getIexOpenTime());
        previousVersionStockData.setIexRealtimePrice(stockData.getIexRealtimePrice());
        previousVersionStockData.setIexRealtimeSize(stockData.getIexRealtimeSize());
        previousVersionStockData.setIexVolume(stockData.getIexVolume());
        previousVersionStockData.setLastTradeTime(stockData.getLastTradeTime());
        previousVersionStockData.setLatestPrice(stockData.getLatestPrice());
        previousVersionStockData.setLatestSource(stockData.getLatestSource());
        previousVersionStockData.setLatestTime(stockData.getLatestTime());
        previousVersionStockData.setLatestUpdate(stockData.getLatestUpdate());
        previousVersionStockData.setLatestVolume(stockData.getLatestVolume());
        previousVersionStockData.setLow(stockData.getLow());
        previousVersionStockData.setLowSource(stockData.getLowSource());
        previousVersionStockData.setLowTime(stockData.getLowTime());
        previousVersionStockData.setMarketCap(stockData.getMarketCap());
        previousVersionStockData.setOddLotDelayedPrice(stockData.getOddLotDelayedPrice());
        previousVersionStockData.setOddLotDelayedPriceTime(stockData.getOddLotDelayedPriceTime());
        previousVersionStockData.setOpen(stockData.getOpen());
        previousVersionStockData.setOpenTime(stockData.getOpenTime());
        previousVersionStockData.setOpenSource(stockData.getOpenSource());
        previousVersionStockData.setPeRatio(stockData.getPeRatio());
        previousVersionStockData.setPreviousClose(stockData.getPreviousClose());
        previousVersionStockData.setPreviousVolume(stockData.getPreviousVolume());
        previousVersionStockData.setPrimaryExchange(stockData.getPrimaryExchange());
        previousVersionStockData.setSymbol(stockData.getSymbol());
        previousVersionStockData.setVolume(stockData.getVolume());
        previousVersionStockData.setWeek52High(stockData.getWeek52High());
        previousVersionStockData.setWeek52Low(stockData.getWeek52Low());
        previousVersionStockData.setYtdChange(stockData.getYtdChange());
        previousVersionStockData.setUSMarketOpen(stockData.isUSMarketOpen());
        return previousVersionStockData;
    }
}