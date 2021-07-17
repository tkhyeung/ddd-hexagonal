package com.example.ddd.domain.repository;

import com.example.ddd.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> findAll();

    Optional<Product> findById(final Long id);

    Product update(final Product product);

    Product save(final Product product);

    void delete(final long id);
}
