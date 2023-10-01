package com.example.stockexchange.repository;

import com.example.stockexchange.entity.ShortCompany;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ShortCompanyRepository extends R2dbcRepository<ShortCompany, Integer> {

    @Query("SELECT * FROM short_company")
    Flux<ShortCompany> getAll();
}
