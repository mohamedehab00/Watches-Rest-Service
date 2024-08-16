package com.rest.watchrestservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
                    authorizationManagerRequestMatcherRegistry.anyRequest().authenticated();
                })
                .oauth2ResourceServer(httpSecurityOAuth2ResourceServerConfigurer -> {
                    httpSecurityOAuth2ResourceServerConfigurer.jwt(Customizer.withDefaults());
                });

        return http.build();
    }
}
