package com.example.ddd.driving_adapter.request;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductRequest {
    @NotNull
    private String productId;
    @NotNull
    private String description;
    @NotNull
    private BigDecimal price;
}
