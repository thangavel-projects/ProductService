package com.asellion.product.rest.api.service;

import com.asellion.product.rest.api.domain.Product;
import com.asellion.product.rest.api.dto.ProductDto;
import com.asellion.product.rest.api.exception.ProductNotFoundException;
import com.asellion.product.rest.api.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    private ModelMapper mapper;


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
    public ProductDto findProductById(int id) throws ProductNotFoundException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product Not Found for " + id));
        return mapper.map(product, ProductDto.class);
    }

    @Override
    public void saveProduct(ProductDto productDto) {
        Product product = mapper.map(productDto, Product.class);
        productRepository.save(product);
    }

    public boolean isServiceEndpointUp() {
        return true;
    }

    public LocalDateTime getAvailableSince() {
        return LocalDateTime.now();
    }
}
