package com.eason.gateway.predicate;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.cloud.gateway.handler.predicate.QueryRoutePredicateFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Component
public class TestRoutePredicateFactory extends AbstractRoutePredicateFactory<TestRoutePredicateFactory.Config> {

    public TestRoutePredicateFactory() {
        super(Config.class);
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return new GatewayPredicate() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                // localhost/api/order/create?userId=1&productId=1&test=1
                ServerHttpRequest request = serverWebExchange.getRequest();
                String param = request.getQueryParams().getFirst(config.param);

                return StringUtils.hasText(param) && param.equals(config.value);
            }
        };
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("param", "value");
    }

    @Validated
    public static class Config {
        public String getParam() {
            return param;
        }

        public void setParam(String param) {
            this.param = param;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        private @NotEmpty String param;
        private @NotEmpty String value;


    }
}
