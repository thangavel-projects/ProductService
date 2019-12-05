package com.asellion.product.rest.api.controller;

import com.asellion.product.rest.api.dto.ProductDto;
import com.asellion.product.rest.api.exception.ProductNotFoundException;
import com.asellion.product.rest.api.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ProductServiceControllerTest {

    private static ProductService productService = Mockito.mock(ProductService.class);

    private static ProductServiceController productServiceController;

    private static List<ProductDto> productDtoList;

    private static ProductDto productDto;

    @BeforeAll
    static void setUp(){
        productServiceController = new ProductServiceController(productService);
        productDtoList = Arrays.asList(new ProductDto(1, "SamsungS8", 2002.86, LocalDateTime.now())
                , new ProductDto(2, "iPhone10", 3718.95, LocalDateTime.now())
                , new ProductDto(3, "HuaweiP20", 1098.51, LocalDateTime.now())
                , new ProductDto(4, "PlueOne", 856.00, LocalDateTime.now()));

        productDto = new ProductDto(1, "SamsungS8", 2002.86, LocalDateTime.now());
    }

    @Test
    void checkReturnsHttpOKStatus() throws JsonProcessingException, ProductNotFoundException {
        when(productService.getAllProducts()).thenReturn(productDtoList);
        ResponseEntity<String> status = productServiceController.getAllProducts();
        assertThat(status.toString(), containsString("OK"));
    }

    @Test
    void checkProductsProperty(){
        when(productService.getAllProducts()).thenReturn(productDtoList);
        List<ProductDto> products = productServiceController.getProducts();
        assertThat(products, Matchers.<ProductDto>hasItem(
                Matchers.both(hasProperty("name", equalTo("SamsungS8")))
                        .and(hasProperty("currentPrice", equalTo(2002.86)))));

    }

    @Test
    void checkProductsReturnsEqualSize(){
        when(productService.getAllProducts()).thenReturn(productDtoList);
        List<ProductDto> products = productServiceController.getProducts();
        assertThat(products.size(), equalTo(4));

    }

    @Test
    void checkProductByIdReturnsData() throws JsonProcessingException, ProductNotFoundException {
        when(productService.findProductById(anyInt())).thenReturn(productDto);
        ProductDto product= productServiceController.getProductById(1);
        assertThat(product, hasProperty("name", equalTo("SamsungS8")));
    }

    @Test
    void verifyProductUpdates() throws JsonProcessingException, ProductNotFoundException {
        when(productService.findProductById(1)).thenReturn(productDto);
        productServiceController.updateProductDetails(1,productDto);
        verify(productService).saveProduct(productDto);
    }

    @Test
    void verifyCreateProduct() throws JsonProcessingException {
        productServiceController.createProduct(productDto);
        verify(productService, times(2)).saveProduct(productDto);
    }

}
