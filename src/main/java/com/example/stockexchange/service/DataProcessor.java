package com.example.stockexchange.service;

import com.example.stockexchange.dao.QuoteDAO;
import com.example.stockexchange.entity.ShortCompany;
import com.example.stockexchange.entity.StockData;
import com.example.stockexchange.repository.SaveCustomRepository;
import com.example.stockexchange.repository.ShortCompanyRepository;
import com.example.stockexchange.utils.PerformanceMonitor;
import com.example.stockexchange.utils.StockQuoteProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
@RequiredArgsConstructor
public class DataProcessor {

    private final StockQuoteProcessor stockQuoteProcessor;
    private final ShortCompanyRepository shortCompanyRepository;
    private final SaveCustomRepository saveCustomRepository;
    private final QuoteDAO quoteDAO;

    @PerformanceMonitor
    @Scheduled(fixedDelayString = "${UPLOAD_COMPANY:360000}", timeUnit = TimeUnit.SECONDS)
    public void uploadCompany() {
        List<ShortCompany> data = stockQuoteProcessor.getListShortCompanies();
        saveCustomRepository.saveCompanies(data);
    }

        @PerformanceMonitor
    @Scheduled(fixedDelayString = "${UPLOAD_STOCK_DATA:5}", timeUnit = TimeUnit.SECONDS)
    public void uploadStockData() {
        Flux<ShortCompany> data = shortCompanyRepository.getAll();

        Flux<StockData> stockData = stockQuoteProcessor.processStockQuotes(data);
        stockQuoteProcessor.savePreviousStockVersion(stockData);
        saveCustomRepository.saveStocks(stockData).subscribe();
    }


    @Scheduled(fixedDelayString = "${SHOW_DATA:5}", timeUnit = TimeUnit.SECONDS)
    public void showStockData() {
        quoteDAO.getTop5MostExpensiveStock();
        quoteDAO.getPriceDifferenceBetweenShares();
    }
}

