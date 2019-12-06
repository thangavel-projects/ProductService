package com.asellion.product.rest.api.controller;

import com.asellion.product.rest.api.exception.InvalidCredentialsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class AuthenticationControllerTest {

    private static final AuthenticationManager authenticationManager = Mockito.mock(AuthenticationManager.class);

    private static final String TEST_USER_NAME = "testUserName";
    private static final String TEST_PASSWORD = "testPassword";
    private static AuthenticationController authenticationController;

    @BeforeAll
    static void setUp(){
        authenticationController = new AuthenticationController(authenticationManager);
    }

    @Test
    void shouldThrowExceptionWhenInvalidCredentials(){
        when(authenticationManager.authenticate(any())).thenThrow(InvalidCredentialsException.class);
        Assertions.assertThrows( InvalidCredentialsException.class, () -> authenticationController.authenticate(TEST_USER_NAME, TEST_PASSWORD));
    }
}
