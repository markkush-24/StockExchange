package com.example.stockexchange.dao;

import com.example.stockexchange.entity.PreviousVersionStockData;
import com.example.stockexchange.entity.StockData;
import com.example.stockexchange.repository.PreviousVersionStockDataRepository;
import com.example.stockexchange.utils.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.example.stockexchange.utils.Converter.convert;

@Repository
@RequiredArgsConstructor
public class PreviousVersionStockDataDao {

    private final PreviousVersionStockDataRepository stockDataRepository;
    private final R2dbcEntityTemplate entityTemplate;

    public Mono<Void> save(PreviousVersionStockData previousVersionStockData) {
        return stockDataRepository.save(previousVersionStockData).then();
    }

    @Transactional
    public Flux<PreviousVersionStockData> save(Flux<StockData> data) {

        return data.map(Converter::convert).doOnNext(entityTemplate::insert);
    }
}
