package com.memposit.auth.oauth.authority;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * The type Uaa application.
 */
@SpringBootApplication
@EnableEurekaClient
@EnableJpaRepositories
@ComponentScan(basePackages = {"memposit.auth.oauth.authority", "memposit.user.service"})
public class UaaApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(UaaApplication.class, args);
    }

}
