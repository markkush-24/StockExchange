package com.example.stockexchange.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;

@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
public class ShortCompany implements Persistable<Integer>, Serializable {

    @Id
    private Integer id;

    private String symbol;
    private String date;
    private String isEnabled;
    private String name;

    @Override
    public boolean isNew() {
        return getId() == null;
    }
}
