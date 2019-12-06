/*
 * Copyright (c) 2019, Asellion. All rights reserved.
 *
 */
package com.asellion.product.rest.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/***
 * This {@code} ProductDto class responsible transferring to service layer to application layer of product data.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto implements Serializable {

    private static final long serialVersionUID = 6911246061642650898L;
    private int id;
    private String name;
    private double currentPrice;
    private LocalDateTime lastUpdate;

}
