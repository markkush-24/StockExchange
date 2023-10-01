package com.example.stockexchange.repository;

import com.example.stockexchange.entity.PreviousVersionStockData;
import com.example.stockexchange.entity.StockData;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Repository
public interface PreviousVersionStockDataRepository extends R2dbcRepository<PreviousVersionStockData, Long> {

//    @Query("SELECT * FROM stock_data WHERE symbol = :symbol")
//    Mono<PreviousVersionStockData> findBySymbol(@Param("symbol") String symbol);

//    @Query("SELECT * FROM stock_data WHERE symbol in (:symbols)")
//    Flux<StockData> findBySymbol(@Param("symbols") Flux<String> symbol);
}
