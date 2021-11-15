package com.example.ddd.contract_test;

import com.example.ddd.domain.service.ProductService;
import com.example.ddd.driving_adapter.ProductController;
import com.example.ddd.driving_adapter.util.ProductApiConverter;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.when;

@SpringBootTest
@Slf4j
public abstract class BaseContractTest {

    //https://cloud.spring.io/spring-cloud-contract/reference/html/

    @Autowired
    private ProductController productController;

    @MockBean
    private ProductService productService;

    @BeforeEach
    public void setup() {
        when(productService.createProduct(
                refEq(ProductApiConverter.requestToProduct(
                        ContractTestDataProvider.createProductRequest()))))
                .thenReturn(ContractTestDataProvider.createProductReturned());

        when(productService.getAllProduct())
                .thenReturn(ContractTestDataProvider.getProducts());

        when(productService.getProduct(9530))
                .thenReturn(ContractTestDataProvider.getProduct());


        StandaloneMockMvcBuilder standaloneMockMvcBuilder
                = MockMvcBuilders.standaloneSetup(productController);
        RestAssuredMockMvc.standaloneSetup(standaloneMockMvcBuilder);
    }
}
