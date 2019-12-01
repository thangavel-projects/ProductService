package com.asellion.product.rest.api.controller;

import com.asellion.product.rest.api.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class ProductViewController {

    private ProductService productService;

    public ProductViewController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/view/products")
    public String displayProductDetails(Map<String, Object> model) {
        var allProductsDetails = productService.getAllProducts();
        model.put("products", allProductsDetails);
        return "product-details";

    }
}
