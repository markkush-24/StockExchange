package com.example.stockexchange.dao;

import com.example.stockexchange.dto.StockDataDto;
import com.example.stockexchange.entity.DifferenceLastPrice;
import com.example.stockexchange.repository.StockDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.stockexchange.mapper.DifferenceLastPriceMapper.objectToDifferenceLastPrice;
import static com.example.stockexchange.utils.QuoteLogger.loggerStock;

@Repository
@RequiredArgsConstructor
public class QuoteDAO {

    private final StockDataRepository stockDataRepository;

    public void getTop5MostExpensiveStock() {
        List<StockDataDto> list = stockDataRepository.getTop5MostExpensiveStock(Pageable.ofSize(5));
        loggerStock(list);
    }

    public void getPriceDifferenceBetweenShares() {
        List<Object[]> list = stockDataRepository.priceDifferenceBetweenShares(Pageable.ofSize(5));
        List<DifferenceLastPrice> differenceLastPriceList = objectToDifferenceLastPrice(list);
        loggerStock(differenceLastPriceList);
    }


}
