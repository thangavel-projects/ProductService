package com.asellion.product.rest.api.service;

import com.asellion.product.rest.api.domain.User;
import com.asellion.product.rest.api.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Slf4j
public class JWTUserService implements UserDetailsService {

    private UserRepository userRepository;

    public JWTUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByName(userName);
        log.info("Fetching {} User Detail from DB", userName);
        return Optional.ofNullable(user)
                .map(a -> new org.springframework.security.core.userdetails.User(a.getName(),
                        a.getPassword(), new ArrayList<>()))
                .orElseThrow(() -> new UsernameNotFoundException("User details not found for " + userName));
    }
}
