package com.asellion.product.rest.api.contoller;

import com.asellion.product.rest.api.exception.ProductNotFoundException;
import com.asellion.product.rest.api.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductServiceController {

    private ProductService productService;

    public ProductServiceController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/api/product-details", produces = "application/json")
    public ResponseEntity<String> getAllProducts() throws JsonProcessingException, ProductNotFoundException {
        return productService.getAllProducts();
    }
}
