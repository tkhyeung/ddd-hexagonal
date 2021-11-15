package com.example.ddd.domain;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class Product {

    private long id;
    private String productId;
    private String description;
    private BigDecimal price;
}
