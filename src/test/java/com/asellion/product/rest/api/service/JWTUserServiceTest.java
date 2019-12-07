package com.asellion.product.rest.api.service;

import com.asellion.product.rest.api.domain.User;
import com.asellion.product.rest.api.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class JWTUserServiceTest {

    private static final UserRepository userRepository = mock(UserRepository.class);
    public static final String AUTH_USER_NAME = "testAsellion";
    public static final String TEST_PASSWORD = "testPassword";

    private static JWTUserService jwtUserService;

    private static User user;
    
    @BeforeAll
    static void setUp(){
        jwtUserService = new JWTUserService(userRepository);
        user = new User(1, AUTH_USER_NAME, TEST_PASSWORD);
    }

    @Test
    void shouldThrowExceptionWhenInvalidUserName(){
        when(userRepository.findByName("userName")).thenReturn(user);
        Assertions.assertThrows(UsernameNotFoundException.class, () -> jwtUserService.loadUserByUsername(AUTH_USER_NAME));
    }
}