package com.example.stockexchange.utils;

import com.example.stockexchange.entity.ShortCompany;
import com.example.stockexchange.entity.StockData;
import com.example.stockexchange.repository.ShortCompanyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class StockQuoteProcessor {

    @Value("${AUTHORIZATION_TOKEN}")
    private String token;


    private final ShortCompanyRepository shortCompanyRepository;

    private static final int INDEX_OF_QUOTE = 0;
    private static final String TRUE = "true";

    public Flux<StockData> processStockQuotes() {
        Flux<ShortCompany> data = shortCompanyRepository.getAll();
        return data.flatMap(this::getListCallable);
    }

    @NotNull
    private Mono<StockData> getListCallable(ShortCompany company) {
        return WebClient.builder()
                .build()
                .get()
                .uri("https://api.iex.cloud/v1/data/core/quote/" + company.getSymbol() + "?token=" + token)
                .retrieve()
                .bodyToFlux(StockData.class).collectList()
                .map(e -> e.get(INDEX_OF_QUOTE))
                .onErrorResume(e -> {
//                    log.error(e.getMessage());
                    return Mono.empty();
                });
    }


    private List<ShortCompany> getCompanies() {
        return WebClient.builder().build().get()
                .uri("https://api.iex.cloud/v1/data/CORE/REF_DATA_IEX_SYMBOLS?token=" + token)
                .retrieve()
                .bodyToFlux(new ParameterizedTypeReference<ShortCompany>() {
                })
                .take(200)
                .toStream()
                .filter(shortCompany -> shortCompany.getIsEnabled().equals(TRUE))
                .collect(Collectors.toList());
    }


    public List<ShortCompany> getListShortCompanies() {
        List<ShortCompany> data = new ArrayList<>();
        try {
            data = getCompanies();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}

