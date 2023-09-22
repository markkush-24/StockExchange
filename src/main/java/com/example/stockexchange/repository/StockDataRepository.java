package com.example.stockexchange.repository;

import com.example.stockexchange.dto.StockDataDto;
import com.example.stockexchange.entity.StockData;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;


@Repository
public interface StockDataRepository extends R2dbcRepository<StockData, Long> {

    @Query("select company_name , previous_volume from (select company_name,previous_volume, id, max(id) over (partition by company_name) as m_id from stock_data ) as a where a.m_id = a.id order by a.previous_volume desc limit 5")
    Flux<StockDataDto> getTop5MostExpensiveStock();

    @Query("SELECT company_name, ABS(MAX(latest_price) - MIN(latest_price))FROM (SELECT company_name, id, latest_price,ROW_NUMBER() OVER (PARTITION BY company_name ORDER BY id DESC) as row_num FROM stock_data) subquery WHERE row_num <= 2 GROUP BY company_name ORDER BY ABS(MAX(latest_price) - MIN(latest_price)) DESC LIMIT 5")
    Flux<Object[]> priceDifferenceBetweenShares();
}
