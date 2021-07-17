package com.example.ddd.driven_adapter.util;

import com.example.ddd.domain.Product;
import com.example.ddd.driven_adapter.entity.ProductEntity;

public class ProductEntityConverter {

    private ProductEntityConverter(){
    }

    public static Product entityToProduct(ProductEntity entity){
        return Product.builder()
                .id(entity.getId())
                .productId(entity.getProductId())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .build();
    }

    public static ProductEntity productToEntity(Product product){
        return ProductEntity.builder()
                .id(product.getId())
                .productId(product.getProductId())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
