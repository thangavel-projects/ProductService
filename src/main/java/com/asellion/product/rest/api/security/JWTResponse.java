package com.asellion.product.rest.api.security;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class JWTResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;

    private final String jwtToken;


}