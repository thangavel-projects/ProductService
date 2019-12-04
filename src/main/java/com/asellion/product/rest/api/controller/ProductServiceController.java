package com.asellion.product.rest.api.controller;

import com.asellion.product.rest.api.dto.ProductDto;
import com.asellion.product.rest.api.exception.ProductNotFoundException;
import com.asellion.product.rest.api.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.locks.StampedLock;

@RestController
@RequestMapping("/api")
public class ProductServiceController {

    private static final ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
    private final StampedLock stampedLock = new StampedLock();

    private ProductService productService;

    public ProductServiceController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/products", produces = "application/json")
    public ResponseEntity<String> getAllProducts() throws JsonProcessingException, ProductNotFoundException {
        long stamp = stampedLock.readLock();
        try {
            var allProducts = productService.getAllProducts();
            return new ResponseEntity<>(writer.writeValueAsString(allProducts), HttpStatus.OK);
        } finally {
            stampedLock.unlock(stamp);
        }
    }

    @GetMapping(value = "/products/{id}", produces = "application/json")
    public ResponseEntity<String> getProductsById(@PathVariable int id) throws JsonProcessingException,
            ProductNotFoundException {
        long stamp = stampedLock.readLock();
        try {
            var productDto = productService.findProductById(id);
            return new ResponseEntity<>(writer.writeValueAsString(productDto), HttpStatus.OK);
        } finally {
            stampedLock.unlock(stamp);
        }
    }

    @PutMapping(value = "/products/{id}", consumes = "application/json")
    public ResponseEntity<String> updateProductDetails(@PathVariable int id, @Valid @RequestBody ProductDto productPayload)
            throws JsonProcessingException, ProductNotFoundException {
        long stamp = stampedLock.writeLock();
        try {
            var productDto = productService.findProductById(id);
            productDto.setName(productPayload.getName());
            productDto.setCurrentPrice(productPayload.getCurrentPrice());
            productService.saveProduct(productDto);
            return new ResponseEntity<>(writer.writeValueAsString("The product details updated for [ " + id + " ]"), HttpStatus.OK);
        }finally {
            stampedLock.unlock(stamp);
        }
    }

    @PostMapping(value = "/products", consumes = "application/json")
    public ResponseEntity<String> createProduct(@Valid @RequestBody ProductDto productDto) throws JsonProcessingException {
        long stamp = stampedLock.writeLock();
        try {
            productService.saveProduct(productDto);
            return new ResponseEntity<>(writer.writeValueAsString("The product resource created for [ " + productDto.getName() + " ]"),
                    HttpStatus.CREATED);
        }finally {
            stampedLock.unlock(stamp);
        }
    }
}
