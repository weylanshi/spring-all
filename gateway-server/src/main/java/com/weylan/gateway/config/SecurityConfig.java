package com.weylan.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
public class SecurityConfig {

  @Bean
  SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) throws Exception {
    http
        .authorizeExchange()
        .pathMatchers("/mybatis/**").hasAuthority("read")
        .anyExchange().authenticated()
        .and()
        .oauth2ResourceServer().and();
//        .jwt();
    return http.build();
  }

}
