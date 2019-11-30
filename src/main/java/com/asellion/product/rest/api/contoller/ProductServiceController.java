package com.asellion.product.rest.api.contoller;

import com.asellion.product.rest.api.dto.ProductDto;
import com.asellion.product.rest.api.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class ProductServiceController {

    private ProductService productService;

    public ProductServiceController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/api/view/product-details")
    public String displayProductDetails(Map<String, List<ProductDto>> model) {
        model.put("products", productService.getAllProducts());
        return "product-details";

    }
}
