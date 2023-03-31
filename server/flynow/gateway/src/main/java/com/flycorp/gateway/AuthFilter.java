package com.flycorp.gateway;

import com.flycorp.lib.security.Token;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.*;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {

    private final WebClient webClient;

    public AuthFilter(WebClient.Builder webClientBuilder) {
        super(Config.class);
        this.webClient = webClientBuilder.build();
    }

    public static class Config {
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (((exchange, chain) -> {
            if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return onError(exchange, HttpStatus.UNAUTHORIZED);
            }

            String tokenHeader = Objects.requireNonNull(exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION)).get(0);
            String [] chunks = tokenHeader.split(" ");

            if (chunks.length != 2 || !chunks[0].equals("Bearer")) {
                return onError(exchange, HttpStatus.UNAUTHORIZED);
            }

            return webClient
                    .get()
                    .uri("http://SECURITY/api/v1/security/validate?token={token}", chunks[1])
                    .retrieve()
                    .bodyToMono(Token.class)
                    .map(t -> {
                        if (t == null || t.getToken() == null) {
                            throw new RuntimeException();
                        }
                        return exchange;
                    }).flatMap(chain::filter);

        }));
    }

    public Mono<Void> onError(ServerWebExchange exchange, HttpStatus status) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(status);
        return response.setComplete();
    }

}
