package com.example.ddd.domain.service;

import com.example.ddd.domain.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProduct();

    Product getProduct(long id);

    Product updateProduct(final Product product);

    Product createProduct(final Product product);

    void deleteProduct(final long id);

}
