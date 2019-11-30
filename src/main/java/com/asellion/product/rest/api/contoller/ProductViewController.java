package com.asellion.product.rest.api.contoller;

import com.asellion.product.rest.api.dto.ProductDto;
import com.asellion.product.rest.api.service.ProductService;
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
    public String displayProductDetails(Map<String, Object> model) {
        List<ProductDto> allProductsDetails = productService.getAllProductsDetails();
        if (!allProductsDetails.isEmpty()) {
            model.put("products", allProductsDetails);
        } else {
            model.put("message", "No Products Available in DB!");
        }
        return "product-details";

    }
}
