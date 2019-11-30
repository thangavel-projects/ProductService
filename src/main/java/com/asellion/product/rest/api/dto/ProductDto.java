package com.asellion.product.rest.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ProductDto implements Serializable {

    private static final long serialVersionUID = 6911246061642650898L;
    private int id;
    private String name;
    private double currentPrice;
    private LocalDateTime lastUpdate;

}
