package com.memposit.api.gateway.service.config;


import com.memposit.api.gateway.service.security.AuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The type Gateway config.
 */
@Configuration
@RequiredArgsConstructor
public class GatewayConfig {

    private final AuthenticationFilter filter;

    /**
     * Routes route locator.
     *
     * @param builder the builder
     * @return the route locator
     */
    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes().route("auth-oauth-authority", r -> r
                        .path("/api/users/**", "/api/auth/**", "/oauth2/authorization/**", "/login/oauth2/code/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://auth-oauth-authority"))
                .route("services-service",
                        r -> r.path("/api/services/**").filters(f -> f.filter(filter)).uri("lb://services-service"))
                .route("order-service",
                        r -> r.path("/api/order/**").filters(f -> f.filter(filter)).uri("lb://order-service"))
                .route("payment-service",
                        r -> r.path("/api/payments/**").filters(f -> f.filter(filter)).uri("lb://payment-service"))
                .build();
    }
}