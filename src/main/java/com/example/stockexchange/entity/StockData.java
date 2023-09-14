package com.example.stockexchange.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StockData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int avgTotalVolume;
    private String calculationPrice;
    private double change;
    private double changePercent;
    private double close;
    private String closeSource;
    private long closeTime;
    private String companyName;
    private String currency;
    private double delayedPrice;
    private long delayedPriceTime;
    private double extendedChange;
    private double extendedChangePercent;
    private double extendedPrice;
    private long extendedPriceTime;
    private double high;
    private String highSource;
    private long highTime;
    private double iexAskPrice;
    private int iexAskSize;
    private double iexBidPrice;
    private int iexBidSize;
    private double iexClose;
    private long iexCloseTime;
    private long iexLastUpdated;
    private double iexMarketPercent;
    private double iexOpen;
    private long iexOpenTime;
    private double iexRealtimePrice;
    private int iexRealtimeSize;
    private int iexVolume;
    private long lastTradeTime;
    private double latestPrice;
    private String latestSource;
    private String latestTime;
    private long latestUpdate;
    private int latestVolume;
    private double low;
    private String lowSource;
    private long lowTime;
    private long marketCap;
    private double oddLotDelayedPrice;
    private long oddLotDelayedPriceTime;
    private double open;
    private long openTime;
    private String openSource;
    private double peRatio;
    private double previousClose;
    private int previousVolume;
    private String primaryExchange;
    private String symbol;
    private int volume;
    private double week52High;
    private double week52Low;
    private double ytdChange;
    private boolean isUSMarketOpen;

}