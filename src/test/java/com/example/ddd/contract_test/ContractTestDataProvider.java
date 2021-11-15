package com.example.ddd.contract_test;

import com.example.ddd.domain.Product;
import com.example.ddd.driving_adapter.request.ProductRequest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class ContractTestDataProvider {

    private ContractTestDataProvider() {

    }

    protected static ProductRequest createProductRequest() {
        return ProductRequest.builder()
                .productId("myUniqueId123")
                .description("description123")
                .price(new BigDecimal(999))
                .build();
    }

    protected static Product createProductReturned() {
        return Product.builder()
                .id(9527)
                .productId("myUniqueId123")
                .description("description123")
                .price(new BigDecimal(999))
                .build();
    }

    protected static List<Product> getProducts() {
        Product p1 = Product.builder()
                .id(9527)
                .productId("myUniqueId123")
                .description("description123")
                .price(new BigDecimal(999))
                .build();

        Product p2 = Product.builder()
                .id(9528)
                .productId("id2")
                .description("desc2")
                .price(new BigDecimal(1000))
                .build();

        Product p3 = Product.builder()
                .id(9529)
                .productId("id3")
                .description("d3")
                .price(new BigDecimal(1001))
                .build();

        return Arrays.asList(p1, p2, p3);
    }

    protected static Product getProduct() {
        Product p = Product.builder()
                .id(9530)
                .productId("9530")
                .description("d9530")
                .price(new BigDecimal(9530))
                .build();

        return p;
    }


}
