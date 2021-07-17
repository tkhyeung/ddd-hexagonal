package com.example.ddd.domain;

import com.example.ddd.domain.repository.ProductRepository;
import com.example.ddd.domain.service.ProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceUnitTest {

    @InjectMocks
    private ProductServiceImpl service;
    @Mock
    private ProductRepository repository;

//    @BeforeEach
//    void setUp() {
//        repository = Mockito.mock(ProductRepository.class);
//        service = new ProductServiceImpl(repository);
//    }


    @Test
    public void getProduct() {
        Product product = Product.builder()
                .id(1)
                .productId("1")
                .description("First Product")
                .price(new BigDecimal(100))
                .build();
        when(repository.findById(eq((long) 1)))
                .thenReturn(Optional.of(product));

        Product retrieved = service.getProduct(1);
        Assertions.assertTrue(product.getId() == retrieved.getId());
        Assertions.assertTrue(product.getDescription() == retrieved.getDescription());
        Assertions.assertTrue(product.getPrice().equals(retrieved.getPrice()));
        Assertions.assertTrue(product.getProductId().equals(retrieved.getProductId()));


    }


    @Test
    public void getAllProduct() {
        List<Product> list = Arrays.asList(
                Product.builder()
                        .id(1)
                        .productId("1")
                        .description("First Product")
                        .price(new BigDecimal(100))
                        .build(),
                Product.builder()
                        .id(2)
                        .productId("2")
                        .description("Second Product")
                        .price(new BigDecimal(200))
                        .build(),
                Product.builder()
                        .id(3)
                        .productId("3")
                        .description("3rd Product")
                        .price(new BigDecimal(300))
                        .build()
        );
        when(repository.findAll()).thenReturn(list);
        Assertions.assertTrue(service.getAllProduct().size() == 3);
    }


    @Test
    public void updateProduct() {
        Product product = Product.builder()
                .id(1)
                .productId("1")
                .description("First Product")
                .price(new BigDecimal(100))
                .build();
        when(repository.update(refEq(product))).thenReturn(product);
        Assertions.assertTrue(service.updateProduct(product).getDescription().equals(product.getDescription()));
    }


    @Test
    public void createProduct() {
        Product product = Product.builder()
                .id(1)
                .productId("1")
                .description("First Product")
                .price(new BigDecimal(100))
                .build();
        service.createProduct(product);
        Mockito.verify(repository, times(1))
                .save(refEq(product));
    }

    @Test
    public void deleteProduct() {
        long id = 1;
        service.deleteProduct(id);
        Mockito.verify(repository, times(1))
                .delete(eq(id));
    }


}
