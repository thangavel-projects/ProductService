package com.asellion.product.rest.api.service;

import com.asellion.product.rest.api.domain.Product;
import com.asellion.product.rest.api.dto.ProductDto;
import com.asellion.product.rest.api.repository.ProductRepository;
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
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> new ProductDto(product.getId(), product.getName(),
                        product.getCurrentPrice(), product.getLastUpdate()))
                .collect(toList());
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
