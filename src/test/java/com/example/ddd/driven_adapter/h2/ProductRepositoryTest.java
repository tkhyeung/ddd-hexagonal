package com.example.ddd.driven_adapter.h2;

import com.example.ddd.domain.Product;
import com.example.ddd.domain.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({ProductRepositoryImpl.class})
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository repository;

    @Test
    @Sql(statements = {
            "INSERT INTO PRODUCTS(ID, PRODUCT_ID, DESCRIPTION, PRICE) VALUES(1, '1', 'FIRST PRODUCT', 100);",
            "INSERT INTO PRODUCTS(ID, PRODUCT_ID, DESCRIPTION, PRICE) VALUES(2, '2', 'SECOND PRODUCT', 200);",
            "INSERT INTO PRODUCTS(ID, PRODUCT_ID, DESCRIPTION, PRICE) VALUES(3, '3', 'THIRD PRODUCT', 300);"
    })
    public void findAllProduct(){
        Assertions.assertTrue(repository.findAll().size() == 3);
    }

    @Test
    @Sql(statements = {
            "INSERT INTO PRODUCTS(ID, PRODUCT_ID, DESCRIPTION, PRICE) VALUES(2, '2', 'SECOND PRODUCT', 200);"
    })
    public void findById(){
        Assertions.assertTrue(repository.findById(2L).get().getDescription().equals("SECOND PRODUCT"));
    }

    @Test
    @Sql(statements = {
            "INSERT INTO PRODUCTS(ID, PRODUCT_ID, DESCRIPTION, PRICE) VALUES(2, '2', 'SECOND PRODUCT', 200);"
    })
    public void update(){
        Product product = Product.builder()
                .id(2L)
                .productId("2")
                .description("UPDATED!")
                .price(BigDecimal.valueOf(200))
                .build();
        Assertions.assertTrue(repository.update(product).getDescription().equals("UPDATED!"));
    }

    @Test
    public void save(){
        Product product = Product.builder()
                .productId("1")
                .description("FIRST PRODUCT")
                .price(BigDecimal.valueOf(100))
                .build();
        Assertions.assertTrue(repository.save(product).getDescription().equals("FIRST PRODUCT"));
    }

    @Test
    @Sql(statements = {
            "INSERT INTO PRODUCTS(ID, PRODUCT_ID, DESCRIPTION, PRICE) VALUES(1, '1', 'FIRST PRODUCT', 100);",
            "INSERT INTO PRODUCTS(ID, PRODUCT_ID, DESCRIPTION, PRICE) VALUES(2, '2', 'SECOND PRODUCT', 200);",
            "INSERT INTO PRODUCTS(ID, PRODUCT_ID, DESCRIPTION, PRICE) VALUES(3, '3', 'THIRD PRODUCT', 300);"
    })
    public void delete(){
        Assertions.assertTrue(repository.findAll().size() == 3);
        repository.delete(3L);
        Assertions.assertTrue(repository.findAll().size() == 2);
    }


















}
