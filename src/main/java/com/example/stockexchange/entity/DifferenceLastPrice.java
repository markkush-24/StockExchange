package com.example.stockexchange.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DifferenceLastPrice {

    private String companyName;
    private double difference;

    @Override
    public String toString() {
        return companyName + ", " + difference;
    }
}
