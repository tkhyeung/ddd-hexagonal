package com.example.ddd.driving_adapter;

import com.example.ddd.domain.Product;
import com.example.ddd.domain.service.ProductService;
import com.example.ddd.driving_adapter.request.ProductRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private ProductService service;

    private <T> String  toJsonString(T clazz) throws JsonProcessingException {
        return mapper.writeValueAsString(clazz);
    }

    @Test
    public void createProduct() throws Exception {
        ProductRequest request = ProductRequest.builder()
                .productId("1")
                .description("first Product")
                .price(BigDecimal.ONE)
                .build();
        Product product = Product.builder()
                .productId(request.getProductId())
                .description(request.getDescription())
                .price(request.getPrice())
                .build();

        when(service.createProduct(refEq(product)))
                .thenReturn(new Product(1L, request.getProductId(), request.getDescription(), request.getPrice()));

        MvcResult result = mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .content(toJsonString(request)))
                .andExpect(status().isCreated()).andReturn();
        Mockito.verify(service, times(1))
                .createProduct(refEq(product));
    }

    @Test
    public void getAllProduct() throws Exception {
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

        when(service.getAllProduct()).thenReturn(list);

        MvcResult result = mockMvc.perform(get("/products"))
                .andExpect(status().isOk()).andReturn();
        Mockito.verify(service, times(1))
                .getAllProduct();
    }

    @Test
    public void updateProduct() throws Exception {
        long id = 1;
        ProductRequest request = ProductRequest.builder()
                .productId("1")
                .description("updated Product desc")
                .price(BigDecimal.ONE)
                .build();

        Product product = Product.builder()
                .id(id)
                .productId(request.getProductId())
                .description(request.getDescription())
                .price(request.getPrice())
                .build();


        when(service.updateProduct(refEq(product)))
                .thenReturn(new Product(id, request.getProductId(), request.getDescription(), request.getPrice()));
        MvcResult result = mockMvc.perform(put("/products/1")
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .content(toJsonString(request)))
                .andExpect(status().isOk()).andReturn();
        Mockito.verify(service, times(1))
                .updateProduct(refEq(product));

    }

    @Test
    public void getProduct() throws Exception {
        long id = 1L;
        Product product = Product.builder()
                .id(id)
                .productId("1")
                .description("updated Product desc")
                .price(BigDecimal.ONE)
                .build();
        when(service.getProduct(eq(id))).thenReturn(product);

        MvcResult result = mockMvc.perform(get("/products/1"))
                .andExpect(status().isOk()).andReturn();

        Mockito.verify(service, times(1))
                .getProduct(eq(id));
    }

    @Test
    public void deleteProduct() throws Exception {
        long id = 1L;

        MvcResult result = mockMvc.perform(delete("/products/1"))
                .andExpect(status().isNoContent()).andReturn();

        Mockito.verify(service, times(1))
                .deleteProduct(eq(id));
    }

}
