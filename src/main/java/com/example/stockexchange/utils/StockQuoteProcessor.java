package com.example.stockexchange.utils;

import com.example.stockexchange.dao.PreviousVersionStockDataDao;
import com.example.stockexchange.dao.StockDataDao;
import com.example.stockexchange.entity.ShortCompany;
import com.example.stockexchange.entity.StockData;
import com.example.stockexchange.repository.PreviousVersionStockDataRepository;
import com.example.stockexchange.service.StocksFeed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class StockQuoteProcessor {

    @Value("${AUTHORIZATION_TOKEN}")
    private String token;


    private final StockDataDao stockDataDao;
    private final StocksFeed stocksFeed;
    private final PreviousVersionStockDataRepository stockDataRepository;
    private final PreviousVersionStockDataDao dao;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;


    private static final int INDEX_OF_QUOTE = 0;
    private static final String TRUE = "true";

    public void savePreviousStockVersion(Flux<StockData> data) {
        stockDataRepository.findBySymbol(data.map(StockData::getSymbol))
                .filter(Objects::nonNull)
                .doOnNext(dao::save).subscribe();
//        data.flatMap(company -> stockDataRepository.findBySymbol(company.getSymbol()))
//                .flatMap(dao::save)
//                .onErrorResume(e -> Mono.empty()).subscribe();
    }

//    public void processStockQuotes(Flux<ShortCompany> data) {
//        data.flatMap(this::getListCallable)
//                .map(quote -> quote.get(INDEX_OF_QUOTE))
//                .flatMap(stockDataDao::save)
//                .onErrorResume(e -> Mono.empty()).subscribe();
//    }

    public Flux<StockData> processStockQuotes(Flux<ShortCompany> data) {

        return data.flatMap(this::getListCallable);

        //return allData.collectList().block();

    }

    @NotNull
    private Mono<StockData> getListCallable(ShortCompany company) {
        return WebClient.builder()
                .build()
                .get()
                .uri("https://api.iex.cloud/v1/data/core/quote/" + company.getSymbol() + "?token=" + token)
                .retrieve()
                .bodyToFlux(StockData.class).collectList()
                .map(e -> e.get(0))
                .onErrorResume(e -> {
                    log.error(e.getMessage());
                    return Mono.empty();
                });
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

