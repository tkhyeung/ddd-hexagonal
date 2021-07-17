package com.example.ddd.driven_adapter.h2;

import com.example.ddd.driven_adapter.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface H2ProductJpaRepository extends JpaRepository<ProductEntity, Long> {
    Optional<ProductEntity> findByProductId(final String productId);
}
