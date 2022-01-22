package org.ok.vid.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import static org.ok.vid.integration.Paths.ACCOUNT_PATH;
import static org.ok.vid.integration.Paths.USER_PATH;
import static org.ok.vid.integration.ServiceNames.ACCOUNT_SERVICE_NAME;
import static org.ok.vid.integration.ServiceNames.USER_SERVICE_NAME;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(ACCOUNT_SERVICE_NAME, r -> r
                        .path("/" + ACCOUNT_PATH + "/**")
                        .uri("lb://" + ACCOUNT_SERVICE_NAME + "/" + ACCOUNT_PATH))
                .route(USER_SERVICE_NAME, r -> r
                        .path("/" + USER_PATH + "/**")
                        .uri("lb://" + USER_SERVICE_NAME + "/" + USER_PATH))
                .build();
    }
}
