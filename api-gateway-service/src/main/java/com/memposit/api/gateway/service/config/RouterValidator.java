package com.memposit.api.gateway.service.config;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

/**
 * The type Router validator.
 */
@Component
public class RouterValidator {

    /**
     * The constant openApiEndpoints.
     */
    public static final List<String> openApiEndpoints = List.of(
            "/auth/signup",
            "/auth/signin",
            "/oauth2/authorization",
            "/login/oauth2/code/"
    );

    /**
     * The Is secured.
     */
    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));

}
