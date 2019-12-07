/*
 * Copyright (c) 2019, Asellion. All rights reserved.
 *
 */
package com.asellion.product.rest.api.config;

import com.asellion.product.rest.api.security.JWTRequestFilter;
import com.asellion.product.rest.api.security.SecurityEntryPoint;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * The {@code} SecurityConfiguration class responsible for whitelisting and blacklisting urls
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${spring.boot.heath.check}")
    private String healthCheck;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SecurityEntryPoint securityEntryPoint;

    @Autowired
    private JWTRequestFilter jwtRequestFilter;

    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * Whitelisting urls to access without any authentication
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
            web
                .ignoring()
                .antMatchers("/console/**")
                .antMatchers("/h2/**")
                .antMatchers("/view/**");
    }

    /**
     * Blacklisting urls to access with any authentication
     * @param httpSecurity
     * @throws Exception
     */

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        if("disable".equalsIgnoreCase(healthCheck)) {
            httpSecurity
                    .authorizeRequests()
                    .antMatchers("/*")
                    .permitAll()
                    .and()
                    .authorizeRequests()
                    .antMatchers("/console/**")
                    .permitAll()
                    .antMatchers("/instances/**")
                    .permitAll()
                    .antMatchers("/actuator/**")
                    .permitAll()
                    .and()
                    .csrf()
                    .disable()
                    .authorizeRequests()
                    .antMatchers("/authenticate")
                    .permitAll()
                    .anyRequest()
                    .authenticated()
                    .and()
                    .exceptionHandling()
                    .authenticationEntryPoint(securityEntryPoint)
                    .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
            httpSecurity.headers().frameOptions().disable();
        }else {
            httpSecurity.authorizeRequests().anyRequest().permitAll()
                    .and().csrf().disable();
        }

    }
}

