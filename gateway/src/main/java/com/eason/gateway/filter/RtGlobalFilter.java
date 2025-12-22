package com.eason.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
public class RtGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        URI uri = request.getURI();
        long currentTimeMillis = System.currentTimeMillis();
        System.out.println("uri: " + uri + " time: " + currentTimeMillis);

        Mono<Void> filter = chain.filter(exchange). // 响应式编程api的响应式流，异步
                doFinally((result) ->{
                    long end = System.currentTimeMillis();
                    System.out.println("耗时：" + (end - currentTimeMillis));
                });
        return filter;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
