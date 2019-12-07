/*
 * Copyright (c) 2019, Asellion. All rights reserved.
 *
 */
package com.asellion.product.rest.api;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

/**
 * The {@code} ProductServiceApplication responsible for start up tomcat
 * server and spring boot admin.
 */
@EnableAdminServer
@Configuration
@SpringBootApplication
public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

}

