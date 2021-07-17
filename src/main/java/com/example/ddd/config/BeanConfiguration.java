package com.example.ddd.config;

import com.example.ddd.domain.repository.ProductRepository;
import com.example.ddd.domain.service.ProductService;
import com.example.ddd.domain.service.ProductServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    ProductService createProductService(final ProductRepository productRepository) {
        return new ProductServiceImpl(productRepository);
    }
}
