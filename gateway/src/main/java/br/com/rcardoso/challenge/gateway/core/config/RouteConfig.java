package br.com.rcardoso.challenge.gateway.core.config;

import br.com.rcardoso.challenge.gateway.core.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {

    @Autowired
    private JwtAuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth", r -> r.path("/auth/**").filters(f -> f.filter(filter)).uri("lb://user-service"))
                .route("alert", r -> r.path("/users/**").filters(f -> f.filter(filter)).uri("lb://user-service"))
                .route("echo", r -> r.path("/wallets/**").filters(f -> f.filter(filter)).uri("lb://wallet-service"))
                .build();
    }
}
