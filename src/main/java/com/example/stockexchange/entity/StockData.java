package com.example.stockexchange.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;

@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StockData implements Persistable<Integer>, Serializable {

    @Id
    private Integer id;

    private int volume;
    private double latestPrice;
    private String symbol;
    private double change;
    private int previousVolume;
    private String companyName;

    @Override
    public boolean isNew() {
        return getId() == null;
    }
}