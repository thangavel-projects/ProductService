package com.asellion.product.rest.api.service;

import com.asellion.product.rest.api.dto.ProductDto;
import com.asellion.product.rest.api.exception.ProductNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface ProductService {

    List<ProductDto> getAllProducts() throws JsonProcessingException, ProductNotFoundException;

    ProductDto findProductById(int id) throws JsonProcessingException, ProductNotFoundException;

    void saveProduct(ProductDto productDto);

}
