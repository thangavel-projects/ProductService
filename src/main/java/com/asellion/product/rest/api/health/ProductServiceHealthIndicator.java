package com.asellion.product.rest.api.health;

import com.asellion.product.rest.api.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Slf4j
public class ProductServiceHealthIndicator implements HealthIndicator {

    private ProductService productService;

    public ProductServiceHealthIndicator(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public Health health() {
        try {
            log.info("Invoking Health Monitor!");
            Health.Builder healthBuilder;
            healthBuilder = productService.isServiceEndpointUp() ? Health.up() : Health.down();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;
            LocalDateTime availableSince = productService.getAvailableSince();
            return healthBuilder.withDetail("up since", dateTimeFormatter.format(availableSince)).build();
        } catch (Exception e) {
            return Health.down(e).build();
        }
    }
}
