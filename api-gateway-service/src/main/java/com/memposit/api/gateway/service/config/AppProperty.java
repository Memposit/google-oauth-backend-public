package com.memposit.api.gateway.service.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Binding AppProperties.
 * To bind all the configurations prefixed with app to a POJO class using Spring Boot’s @ConfigurationProperties
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "app")
public class AppProperty {

    private List<String> authorizedRedirectUris = new ArrayList<>();

    private String tokenSecret;

    private long tokenExpirationMsec;
}
