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
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductServiceController {

    private static final ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();

    private ProductService productService;

    public ProductServiceController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/products", produces = "application/json")
    public ResponseEntity<String> getAllProducts() throws JsonProcessingException, ProductNotFoundException {
        List<ProductDto> allProducts = productService.getAllProducts();
        return new ResponseEntity<>(writer.writeValueAsString(allProducts), HttpStatus.OK);
    }

    @GetMapping(value = "/products/{id}", produces = "application/json")
    public ResponseEntity<String> getProductsById(@PathVariable int id) throws JsonProcessingException,
            ProductNotFoundException {
        ProductDto productDto = productService.findProductById(id);
        return new ResponseEntity<>(writer.writeValueAsString(productDto), HttpStatus.OK);

    }

    @PutMapping(value = "/products/{id}", consumes = "application/json")
    public ResponseEntity<String> updateProductDetails(@PathVariable int id, @Valid @RequestBody ProductDto productDto)
            throws JsonProcessingException, ProductNotFoundException {
        ProductDto productDtoById = productService.findProductById(id);
        productDtoById.setName(productDto.getName());
        productDtoById.setCurrentPrice(productDto.getCurrentPrice());
        productService.saveProduct(productDtoById);
        return new ResponseEntity<>(writer.writeValueAsString("The product details updated for [ " + id + " ]"), HttpStatus.OK);
    }

    @PostMapping(value = "/products", consumes = "application/json")
    public ResponseEntity<String> createProduct(@Valid @RequestBody ProductDto productDto) throws JsonProcessingException {
        productService.saveProduct(productDto);
        return new ResponseEntity<>(writer.writeValueAsString("The product resource created for [ " + productDto.getName() + " ]"),
                HttpStatus.CREATED);
    }
}
