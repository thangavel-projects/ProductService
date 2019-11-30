package com.asellion.product.rest.api.service;

import com.asellion.product.rest.api.domain.Product;
import com.asellion.product.rest.api.dto.ProductDto;
import com.asellion.product.rest.api.exception.ProductNotFoundException;
import com.asellion.product.rest.api.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDto> getAllProductsDetails() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> new ProductDto(product.getId(), product.getName(),
                        product.getCurrentPrice(), product.getLastUpdate()))
                .collect(toList());
    }

    @Override
    public ResponseEntity<String> getAllProducts() throws JsonProcessingException, ProductNotFoundException {
        List<Product> products = productRepository.findAll();
        ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
        if (!products.isEmpty()) {
            return new ResponseEntity<>(writer.writeValueAsString(products), HttpStatus.OK);
        }
        throw new ProductNotFoundException("No Records Available!");
    }

    @Override
    public Optional<ProductDto> findProductById(int id) {
        return Optional.empty();
    }

    @Override
    public void updateProductById(int id) {

    }

    @Override
    public void save(ProductDto productDto) {

    }
}
