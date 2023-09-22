package com.example.stockexchange.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
public class ShortCompany {
    @Id
    private Long id;

    private String symbol;
    private String date;
    private String isEnabled;
    private String name;
}
