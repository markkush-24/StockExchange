package com.example.stockexchange.repository;

import com.example.stockexchange.entity.ShortCompany;
import com.example.stockexchange.entity.StockData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class SaveCustomRepository implements CustomRepository {

    private final R2dbcEntityTemplate r2dbcEntityTemplate;

    @Override
    public void saveCompanies(List<ShortCompany> companies) {
        companies.forEach(company -> r2dbcEntityTemplate.insert(company)
                .subscribe());
    }

    @Override
    @Transactional
    public Flux<StockData> saveStocks(Flux<StockData> stocks) {
        return stocks.filter(Objects::nonNull).doOnNext(r2dbcEntityTemplate::insert);
    }
}
