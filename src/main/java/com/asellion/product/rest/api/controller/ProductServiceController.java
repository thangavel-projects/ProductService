/*
 * Copyright (c) 2019, Asellion. All rights reserved.
 *
 */
package com.asellion.product.rest.api.controller;

import com.asellion.product.rest.api.dto.ProductDto;
import com.asellion.product.rest.api.exception.ProductNotFoundException;
import com.asellion.product.rest.api.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.locks.StampedLock;

/**
 * The {@code} ProductServiceController handles all GET, POST and PUT method calls
 * for creating, updating resources through secured end point url.
 */

@RestController
@RequestMapping("/api")
@Slf4j
public class ProductServiceController {

    private static final ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
    private final StampedLock stampedLock = new StampedLock();

    private ProductService productService;

    public ProductServiceController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Invoke H2 DB and returns all the products and throws exception if any error
     * @return String
     * @throws JsonProcessingException
     */

    @GetMapping(value = "/products", produces = "application/json")
    public ResponseEntity<String> getAllProducts() throws JsonProcessingException {
        log.info("Started fetching all products from DB!");
        long stamp = stampedLock.readLock();
        try {
            var allProducts = getProducts();
            log.info("Retrieval of all products success and returned {} products", allProducts.size());
            return new ResponseEntity<>(writer.writeValueAsString(allProducts), HttpStatus.OK);
        } finally {
            stampedLock.unlock(stamp);
        }
    }

    /**
     * Invoke H2 DB and returns particular products based on ID and throws exception if any error
     * @return String
     * @throws JsonProcessingException
     */

    @GetMapping(value = "/products/{id}", produces = "application/json")
    public ResponseEntity<String> getProductsById(@PathVariable int id) throws JsonProcessingException,
            ProductNotFoundException {
        log.info("Started fetching products {} by id", id);
        long stamp = stampedLock.readLock();
        try {
            var productDto = getProductById(id);
            log.info("Products returned for the {} id is {}", id, productDto);
            return new ResponseEntity<>(writer.writeValueAsString(productDto), HttpStatus.OK);
        } finally {
            stampedLock.unlock(stamp);
        }
    }

    /**
     * Invoke H2 DB and updates particular products based on ID and throws exception if any error
     * @return String
     * @throws JsonProcessingException
     */

    @PutMapping(value = "/products/{id}", consumes = "application/json")
    public ResponseEntity<String> updateProductDetails(@PathVariable int id, @Valid @RequestBody ProductDto productPayload)
            throws JsonProcessingException, ProductNotFoundException {
        log.info("Updating the product id {} and the payload is {}", id, productPayload);
        long stamp = stampedLock.writeLock();
        try {
            var productDto = getProductById(id);
            productDto.setName(productPayload.getName());
            productDto.setCurrentPrice(productPayload.getCurrentPrice());
            productService.saveProduct(productDto);
            log.info("Product update was successful for {} id", id);
            return new ResponseEntity<>(writer.writeValueAsString("The product details updated for [ " + id + " ]"), HttpStatus.OK);
        }finally {
            stampedLock.unlock(stamp);
        }
    }

    /**
     * Create a product in the H2 DB and throws exception if any error
     * @return String
     * @throws JsonProcessingException
     */

    @PostMapping(value = "/products", consumes = "application/json")
    public ResponseEntity<String> createProduct(@Valid @RequestBody ProductDto productDto) throws JsonProcessingException {
        log.info("Creating product process is {} started", productDto.getName());
        long stamp = stampedLock.writeLock();
        try {
            productService.saveProduct(productDto);
            log.info("Creating product process completed!");
            return new ResponseEntity<>(writer.writeValueAsString("The product resource created for [ " + productDto.getName() + " ]"),
                    HttpStatus.CREATED);
        }finally {
            stampedLock.unlock(stamp);
        }
    }
    // The below 2 methods are public because of Unit Testing
    public List<ProductDto> getProducts() {
        return productService.getAllProducts();
    }
    public ProductDto getProductById(@PathVariable int id) throws JsonProcessingException, ProductNotFoundException {
        return productService.findProductById(id);
    }
}
