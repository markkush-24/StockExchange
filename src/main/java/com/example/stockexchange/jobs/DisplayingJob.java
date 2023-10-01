package com.example.stockexchange.jobs;

import com.example.stockexchange.service.LoggerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
@RequiredArgsConstructor
public class DisplayingJob {

    private final LoggerService loggerService;

    @Scheduled(fixedDelayString = "${SHOW_DATA:5}", timeUnit = TimeUnit.SECONDS)
    public void showStockData() {
        loggerService.getTop5MostExpensiveStock();
        loggerService.getPriceDifferenceBetweenShares();
    }
}

