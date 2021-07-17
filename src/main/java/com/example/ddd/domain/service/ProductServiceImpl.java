package com.example.ddd.domain.service;

import com.example.ddd.domain.Product;
import com.example.ddd.domain.repository.ProductRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

//Not @service and not @Autowired the repository because Spring configuration is not inside the Domain
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    @NonNull
    private ProductRepository repository;

    @Override
    public List<Product> getAllProduct() {
        return repository.findAll();
    }

    @Override
    public Product getProduct(long id) {
//        return repository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        return repository.findById(id).orElseGet(() -> new Product());
    }

    @Override
    public Product updateProduct(Product product) {
        return repository.update(product);
    }

    @Override
    public Product createProduct(Product product) {
        return repository.save(product);
    }

    @Override
    public void deleteProduct(long id) {
        repository.delete(id);
    }
}
