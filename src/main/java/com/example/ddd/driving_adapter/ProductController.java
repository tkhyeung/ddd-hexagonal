package com.example.ddd.driving_adapter;

import com.example.ddd.domain.Product;
import com.example.ddd.domain.service.ProductService;
import com.example.ddd.driving_adapter.request.ProductRequest;
import com.example.ddd.driving_adapter.response.ProductResponse;
import com.example.ddd.driving_adapter.response.ProductsResponse;
import com.example.ddd.driving_adapter.util.ProductApiConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "products")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestBody ProductRequest request) {
        log.info("Received Request: create product {}", request);
        Product product = service.createProduct(ProductApiConverter.requestToProduct(request));
        log.info("Created product {}", product.toString());
        return ProductApiConverter.productToResponse(product);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse updateProduct(@PathVariable("id") long id, @RequestBody ProductRequest request) {
        log.info("Received Request: update product {}", request);
        Product product = service.updateProduct(ProductApiConverter.requestToProduct(id, request));
        log.info("Updated product {}", product.toString());
        return ProductApiConverter.productToResponse(product);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ProductsResponse getProducts() {
        log.info("Received Request: get products");
        List<Product> list = service.getAllProduct();
        log.info("Retrieved Products: {}", list.toString());
        return ProductApiConverter.productsToResponses(list);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse getProduct(@PathVariable long id) {
        log.info("Received Request: get product {}", id);
        Product product = service.getProduct(id);
        log.info("Retrieved Product: {}", product.toString());
        return ProductApiConverter.productToResponse(product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable final long id) {
        log.info("Received Request: delete product {}", id);
        service.deleteProduct(id);
    }
}
