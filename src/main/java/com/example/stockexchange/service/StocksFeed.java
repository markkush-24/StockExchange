package com.example.stockexchange.service;

import com.example.stockexchange.entity.ShortCompany;
import com.example.stockexchange.entity.StockData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "client", url = "https://studyexchanger1.iex.cloud/v1")
public interface StocksFeed {

    @GetMapping("data/CORE/REF_DATA_IEX_SYMBOLS")
    List<ShortCompany> getCompanies(@RequestParam("token") String id);

    @GetMapping("data/core/quote/{id}")
    List<StockData> getQuote(@PathVariable("id") String id, @RequestParam("token") String token);
}
