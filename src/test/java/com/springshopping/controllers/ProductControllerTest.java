package com.springshopping.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import java.util.Optional;

import java.math.BigDecimal;
import java.util.*;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.springshopping.controllers.ProductController;
import com.springshopping.entities.Product;
import com.springshopping.service.ProductService;

public class ProductControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    void testSearchProductById() throws Exception {
        Long productId = 1L;
        Product mockProduct = new Product(productId, "Test Product", "Test Description", BigDecimal.valueOf(9.99));

        when(productService.getProductById(productId)).thenReturn(Optional.of(mockProduct));

        mockMvc.perform(get("/search").param("id", productId.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(productId))
                .andExpect(jsonPath("$.name").value("Test Product"));

        verify(productService).getProductById(productId);
    }

    @Test
    void testSearchProductByName() throws Exception {
        String productName = "Test Product";
        List<Product> productList = Arrays.asList(
                new Product(1L, productName, "Test Description 1", BigDecimal.valueOf(9.99)),
                new Product(2L, productName, "Test Description 2", BigDecimal.valueOf(19.99)));

        when(productService.getProductsByName(productName)).thenReturn(productList);

        mockMvc.perform(get("/search").param("name", productName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(productName))
                .andExpect(jsonPath("$[1].name").value(productName));

        verify(productService).getProductsByName(productName);
    }

}
