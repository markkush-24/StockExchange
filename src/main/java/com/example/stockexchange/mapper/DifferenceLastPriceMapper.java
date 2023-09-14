package com.example.stockexchange.mapper;

import com.example.stockexchange.entity.DifferenceLastPrice;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DifferenceLastPriceMapper {

    @NotNull
    public static List<DifferenceLastPrice> objectToDifferenceLastPrice(List<Object[]> list) {
        return list.stream()
                .map(objArray -> new DifferenceLastPrice((String) objArray[0], (Double) objArray[1]))
                .collect(Collectors.toList());
    }
}
