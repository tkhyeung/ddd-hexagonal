package com.example.ddd.driven_adapter.h2;

import com.example.ddd.domain.Product;
import com.example.ddd.domain.repository.ProductRepository;
import com.example.ddd.driven_adapter.entity.ProductEntity;
import com.example.ddd.driven_adapter.util.ProductEntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Primary
public class ProductRepositoryImpl implements ProductRepository {

    @Autowired
    private H2ProductJpaRepository repository;

    @Override
    public List<Product> findAll() {
        List<ProductEntity> productEntityList = repository.findAll();
        return productEntityList.stream()
                .map(ProductEntityConverter::entityToProduct)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Product> findById(Long id) {
        Optional<ProductEntity> productEntity = repository.findById(id);
        return productEntity.map(ProductEntityConverter::entityToProduct);
    }

    @Override
    public Product update(Product product) {
        ProductEntity newProductEntity = repository.save(ProductEntityConverter.productToEntity(product));
        return ProductEntityConverter.entityToProduct(newProductEntity);
    }

    @Override
    public Product save(Product product) {
        ProductEntity productEntity = repository.save(ProductEntityConverter.productToEntity(product));
        return ProductEntityConverter.entityToProduct(productEntity);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }
}
