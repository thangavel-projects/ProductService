package com.asellion.product.rest.api.service;

import com.asellion.product.rest.api.domain.User;
import com.asellion.product.rest.api.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JWTUserService implements UserDetailsService {

    private UserRepository userRepository;

    public JWTUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByName(userName);
        if(user == null){
            throw new UsernameNotFoundException("User details not found for " + userName);
        }
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(),
                new ArrayList<>());
    }
}
