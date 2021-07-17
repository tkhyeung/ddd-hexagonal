package com.example.ddd.driving_adapter.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@AllArgsConstructor
@ToString
@Builder
public class ProductsResponse {
    private List<ProductResponse> products;
}
