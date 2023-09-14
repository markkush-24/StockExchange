package com.example.stockexchange.service;

import com.example.stockexchange.dao.QuoteDAO;
import com.example.stockexchange.entity.ShortCompany;
import com.example.stockexchange.repository.ShortCompanyRepository;
import com.example.stockexchange.utils.StockQuoteProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
@RequiredArgsConstructor
public class DataProcessor {

    private final StockQuoteProcessor stockQuoteProcessor;
    private final ShortCompanyRepository shortCompanyRepository;
    private final QuoteDAO quoteDAO;

    @Scheduled(fixedDelayString = "${UPLOAD_COMPANY:360000}", timeUnit = TimeUnit.SECONDS)
    public void uploadCompany() {
        List<ShortCompany> data = stockQuoteProcessor.getListShortCompanies();
        shortCompanyRepository.saveAll(data);
    }


    @Scheduled(fixedDelayString = "${UPLOAD_STOCK_DATA:5}", timeUnit = TimeUnit.SECONDS)
    public void uploadStockData() {
        List<ShortCompany> data = shortCompanyRepository.getAll();
        stockQuoteProcessor.processStockQuotes(data);
    }


    @Scheduled(fixedDelayString = "${UPLOAD_STOCK_DATA:5}", timeUnit = TimeUnit.SECONDS)
    public void showStockData() {
        quoteDAO.getTop5MostExpensiveStock();
        quoteDAO.getPriceDifferenceBetweenShares();
    }
}

