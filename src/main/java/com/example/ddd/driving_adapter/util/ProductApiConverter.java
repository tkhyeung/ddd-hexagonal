package com.example.ddd.driving_adapter.util;

import com.example.ddd.domain.Product;
import com.example.ddd.driving_adapter.request.ProductRequest;
import com.example.ddd.driving_adapter.response.ProductResponse;
import com.example.ddd.driving_adapter.response.ProductsResponse;

import java.util.List;
import java.util.stream.Collectors;

public class ProductApiConverter {

    private ProductApiConverter() {
    }

    public static Product requestToProduct(ProductRequest request) {
        return Product.builder()
                .productId(request.getProductId())
                .description(request.getDescription())
                .price(request.getPrice())
                .build();
    }

    public static Product requestToProduct(long id, ProductRequest request) {
        return Product.builder()
                .id(id)
                .productId(request.getProductId())
                .description(request.getDescription())
                .price(request.getPrice())
                .build();
    }


    public static ProductResponse productToResponse(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .productId(product.getProductId())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

    public static ProductsResponse productsToResponses(List<Product> list){
        List<ProductResponse> productResponseList = list.stream().map(r -> ProductResponse.builder()
                .id(r.getId())
                .productId(r.getProductId())
                .description(r.getDescription())
                .price(r.getPrice())
                .build()).collect(Collectors.toList());
        return ProductsResponse.builder().products(productResponseList).build();
    }
}
