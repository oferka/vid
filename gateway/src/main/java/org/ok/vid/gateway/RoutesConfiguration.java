package org.ok.vid.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.ok.vid.integration.Paths.ACCOUNT_PATH;
import static org.ok.vid.integration.Paths.USER_PATH;
import static org.ok.vid.integration.ServiceNames.ACCOUNT_SERVICE_NAME;
import static org.ok.vid.integration.ServiceNames.USER_SERVICE_NAME;

@Configuration
public class RoutesConfiguration {

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
