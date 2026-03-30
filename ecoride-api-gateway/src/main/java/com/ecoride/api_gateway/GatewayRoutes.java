package com.ecoride.api_gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.filter.LoadBalancerFilterFunctions.lb;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;
import static org.springframework.web.servlet.function.RequestPredicates.path;

@Configuration
public class GatewayRoutes {

    @Bean
    public RouterFunction<ServerResponse> userServiceRoute() {
        return route("user-service")
                .route(path("/api/v1/users"), http())
                .route(path("/api/v1/users/**"), http())
                .filter(lb("user-service"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> vehicleServiceRoute() {
        return route("vehicle-service")
                .route(path("/api/v1/vehicles"), http())
                .route(path("/api/v1/vehicles/**"), http())
                .filter(lb("vehicle-service"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> maintenanceServiceRoute() {
        return route("maintenance-service")
                .route(path("/api/v1/maintenance"), http())
                .route(path("/api/v1/maintenance/**"), http())
                .filter(lb("maintenance-service"))
                .build();
    }
}