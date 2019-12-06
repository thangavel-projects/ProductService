/*
 * Copyright (c) 2019, Asellion. All rights reserved.
 *
 */
package com.asellion.product.rest.api.repository;

import com.asellion.product.rest.api.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * This {@code} UserRepository class responsible for handling interacting User Repository
 */

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query( value = "SELECT * FROM USER_DETAILS WHERE userName = ?1", nativeQuery = true)
    User findByName(String username);

}
