/*
 * Copyright (c) 2019, Asellion. All rights reserved.
 *
 */

package com.asellion.product.rest.api.repository;

import com.asellion.product.rest.api.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This {@code} ProductRepository class responsible for handling interacting Product Repository
 */

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
