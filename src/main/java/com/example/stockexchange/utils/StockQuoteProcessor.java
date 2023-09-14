package com.example.stockexchange.utils;

import com.example.stockexchange.dao.StockDataDao;
import com.example.stockexchange.entity.ShortCompany;
import com.example.stockexchange.entity.StockData;
import com.example.stockexchange.service.StocksFeed;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class StockQuoteProcessor {

    @Value("${AUTHORIZATION_TOKEN}")
    private String token;


    private final StockDataDao stockDataDao;
    private final StocksFeed stocksFeed;

    private static final int INDEX_OF_QUOTE = 0;
    private static final String TRUE = "true";


    public void processStockQuotes(List<ShortCompany> data) {
        ExecutorService threadPool = Executors.newCachedThreadPool();

        for (ShortCompany company : data) {
            threadPool.execute(() -> {
                try {
                    List<StockData> quote = stocksFeed.getQuote(company.getSymbol(), token);
                    stockDataDao.save(quote.get(INDEX_OF_QUOTE));
                    log.info(company.getSymbol());
                } catch (FeignException.TooManyRequests ignored) {
                } catch (Exception e) {
                    log.info("Ошибка при выполнении запроса для символа " + company.getSymbol() + ": " + e.getMessage());
                }
            });
        }
        threadPool.shutdown();
    }

    public List<ShortCompany> getListShortCompanies() {
        List<ShortCompany> data = new ArrayList<>();
        try {
            data = stocksFeed.getCompanies(token)
                    .stream()
                    .filter(shortCompany -> shortCompany.getIsEnabled().equals(TRUE))
                    .limit(200)
                    .collect(Collectors.toList());
        } catch (Exception ignored) {
        }
        return data;
    }
}

