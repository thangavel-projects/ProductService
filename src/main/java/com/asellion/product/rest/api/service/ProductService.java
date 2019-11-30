package com.asellion.product.rest.api.service;

import com.asellion.product.rest.api.dto.ProductDto;
import com.asellion.product.rest.api.exception.ProductNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<ProductDto> getAllProductsDetails();

    ResponseEntity<String> getAllProducts() throws JsonProcessingException, ProductNotFoundException;

    Optional<ProductDto> findProductById(int id);

    void updateProductById(int id);

    void save(ProductDto productDto);


}
