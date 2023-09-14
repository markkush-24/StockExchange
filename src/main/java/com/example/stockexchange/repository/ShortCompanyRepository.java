package com.example.stockexchange.repository;

import com.example.stockexchange.entity.ShortCompany;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShortCompanyRepository extends CrudRepository<ShortCompany, Long> {
    @Query("SELECT s FROM ShortCompany s")
    List<ShortCompany>getAll();
}
