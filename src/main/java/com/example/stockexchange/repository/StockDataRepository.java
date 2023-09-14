package com.example.stockexchange.repository;

import com.example.stockexchange.dto.StockDataDto;
import com.example.stockexchange.entity.StockData;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockDataRepository extends CrudRepository<StockData, Long> {

    @Query("SELECT new com.example.stockexchange.dto.StockDataDto(s.companyName, s.previousVolume) FROM StockData s WHERE s.id = (SELECT MAX(id) FROM StockData WHERE companyName = s.companyName) ORDER BY s.previousVolume DESC")
    List<StockDataDto> getTop5MostExpensiveStock(Pageable page);

    @Query(value = "SELECT company_name, ABS(MAX(latest_price) - MIN(latest_price)) FROM (SELECT company_name, id, latest_price, ROW_NUMBER() OVER (PARTITION BY company_name ORDER BY id DESC) as row_num  FROM stock_data) subquery " +
            "WHERE row_num <= 2 " +
            "GROUP BY company_name " +
            "ORDER BY ABS(MAX(latest_price) - MIN(latest_price)) DESC"
            , nativeQuery = true)
    List<Object[]> priceDifferenceBetweenShares(Pageable page);
}
