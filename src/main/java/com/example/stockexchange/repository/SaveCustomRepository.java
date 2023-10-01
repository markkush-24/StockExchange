package com.example.stockexchange.repository;

import com.example.stockexchange.entity.PreviousVersionStockData;
import com.example.stockexchange.entity.ShortCompany;
import com.example.stockexchange.entity.StockData;
import com.example.stockexchange.utils.Converter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class SaveCustomRepository implements CustomRepository {

    private final StockDataRepository stockDataRepository;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;

    private static final Logger logger = LoggerFactory.getLogger(SaveCustomRepository.class);

    @Override
    @Transactional
    public Mono<Void> saveCompanies(List<ShortCompany> companies) {
        return Flux.fromIterable(companies)
                .flatMap(r2dbcEntityTemplate::insert)
                .then();
    }

    @Override
    @Transactional
    public Flux<StockData> saveStocks(Flux<StockData> stocks) {
        return stocks.filter(Objects::nonNull)
                .flatMap(stock -> r2dbcEntityTemplate.insert(stock)
                        .doOnSuccess(v -> logger.info("Inserted stock: {}", stock)));
    }

    @Override
    @Transactional
    public Flux<PreviousVersionStockData> savePreviousVersionStockData(Flux<StockData> stockData) {
        Flux<StockData> stockDataFlux = stockDataRepository.findBySymbol(List.of("A", "AA", "AAA"));
        stockDataFlux = stockDataFlux.filter(Objects::nonNull);
        Flux<PreviousVersionStockData> previousStockDataFlux = stockDataFlux.map(Converter::convert);
        return previousStockDataFlux.flatMap(previousStock -> {
                previousStock.setId(0);
                return r2dbcEntityTemplate.insert(previousStock)
                        .doOnSuccess(v -> logger.info("Inserted previous stock: {}", previousStock));
        });
    }
}
