package com.asellion.product.rest.api.service;

import com.asellion.product.rest.api.dto.ProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<ProductDto> getAllProducts();

    Optional<ProductDto> findProductById(int id);

    void updateProductById(int id);

    void save(ProductDto productDto);
}
