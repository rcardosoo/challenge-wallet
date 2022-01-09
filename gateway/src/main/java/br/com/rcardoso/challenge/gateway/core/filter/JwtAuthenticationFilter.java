package br.com.rcardoso.challenge.gateway.core.filter;

import br.com.rcardoso.challenge.gateway.core.exceptions.JwtTokenMalformedException;
import br.com.rcardoso.challenge.gateway.core.exceptions.JwtTokenMissingException;
import br.com.rcardoso.challenge.gateway.core.service.IJwtService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Component
public class JwtAuthenticationFilter implements GatewayFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String USER_ID_CLAIM = "id";
    private static final String TOKEN_PREFIX = "Bearer";

    @Autowired
    private IJwtService jwtService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        var request = exchange.getRequest();

        if (isApiSecured(request)) {
            if (!request.getHeaders().containsKey(AUTHORIZATION_HEADER)) {
                return returnFilterException(exchange, UNAUTHORIZED);
            }

            final String token = request.getHeaders()
                    .getOrEmpty(AUTHORIZATION_HEADER)
                    .get(0)
                    .replace(TOKEN_PREFIX, "");

            try {
                jwtService.validateToken(token);
            } catch (JwtTokenMalformedException | JwtTokenMissingException e) {
                return returnFilterException(exchange, BAD_REQUEST);
            }

            Claims claims = jwtService.getClaims(token);
            exchange.getRequest().mutate().header(USER_ID_CLAIM, String.valueOf(claims.get(USER_ID_CLAIM))).build();
        }

        return chain.filter(exchange);
    }

    private boolean isApiSecured(ServerHttpRequest request) {
        final List<String> apiEndpoints = List.of("/users", "/auth");

        Predicate<ServerHttpRequest> isApiSecured =
                r -> apiEndpoints.stream()
                        .noneMatch(uri -> r.getURI().getPath().equals(uri) && Objects.equals(r.getMethod(), POST));

        return isApiSecured.test(request);
    }

    private Mono<Void> returnFilterException(ServerWebExchange exchange, HttpStatus status) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(status);
        return response.setComplete();
    }

}
