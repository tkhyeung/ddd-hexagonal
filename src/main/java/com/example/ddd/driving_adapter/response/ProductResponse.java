package com.example.ddd.driving_adapter.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@ToString
@Builder
public class ProductResponse {
    private Long id;
    private String productId;
    private String description;
    private BigDecimal price;
}
