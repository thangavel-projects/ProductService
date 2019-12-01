package com.asellion.product.rest.api.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorDetails {
    private String message;
    private LocalDateTime localDateTime;
}
