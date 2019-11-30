package com.asellion.product.rest.api.controller;

import com.asellion.product.rest.api.dto.ProductDto;
import com.asellion.product.rest.api.exception.ProductNotFoundException;
import com.asellion.product.rest.api.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class ProductViewController {

    private ProductService productService;

    public ProductViewController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/view/product-details")
    public String displayProductDetails(Map<String, Object> model) throws JsonProcessingException,
            ProductNotFoundException {
        List<ProductDto> allProductsDetails = productService.getAllProducts();
        if (!allProductsDetails.isEmpty()) {
            model.put("products", allProductsDetails);
        } else {
            model.put("message", "No Products Available in DB!");
        }
        return "product-details";

    }
}
