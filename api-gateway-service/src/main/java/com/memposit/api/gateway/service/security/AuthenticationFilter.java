package com.memposit.api.gateway.service.security;

import com.memposit.api.gateway.service.config.RouterValidator;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

/**
 * The type Authentication filter.
 */
@Component
@RequiredArgsConstructor
public class AuthenticationFilter implements GatewayFilter {
    private final TokenHelper tokenHelper;
    private final RouterValidator routerValidator;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        if (routerValidator.isSecured.test(request)) {
            final String token = getJwtFromRequest(request).get();

            if (token == null)
                return this.onError(exchange, "Authorization header/token is missing in request", HttpStatus.UNAUTHORIZED);

            if (!tokenHelper.validateToken(token))
                return this.onError(exchange, "Authorization header is invalid", HttpStatus.UNAUTHORIZED);

            this.populateRequestWithHeaders(exchange, token);
        }
        return chain.filter(exchange);
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

    private void populateRequestWithHeaders(ServerWebExchange exchange, String token) {
        Claims claims = tokenHelper.getClaims(token);
        exchange.getRequest().mutate()
                .header("id", String.valueOf(claims.getSubject()))
                .header("role", String.valueOf(claims.get("role")))
                .build();
    }

    private Optional<String> getJwtFromRequest(ServerHttpRequest request) {
        List<String> authHeaders = request.getHeaders().get("Authorization");
        if (!authHeaders.isEmpty()) {
            String bearerToken = authHeaders.get(0);
            if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
                return Optional.of(bearerToken.substring(7));
            }
        }
        return Optional.empty();
    }
}
