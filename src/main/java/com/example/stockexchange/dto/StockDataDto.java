package com.example.stockexchange.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockDataDto {

    private String companyName;
    private int previousVolume;

    @Override
    public String toString() {
        return companyName + ", " + previousVolume;
    }
}
