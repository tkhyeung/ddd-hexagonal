package com.example.ddd.driving_adapter.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Builder
@Getter
@AllArgsConstructor
@ToString
public class ProductRequest {
    @NotNull
    private String productId;
    @NotNull
    private String description;
    @NotNull
    private BigDecimal price;
}
