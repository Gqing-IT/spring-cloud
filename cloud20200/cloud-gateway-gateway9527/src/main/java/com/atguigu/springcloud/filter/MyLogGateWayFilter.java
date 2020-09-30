package com.atguigu.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;

import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.lang.annotation.Annotation;

@Component
@Slf4j
public class MyLogGateWayFilter implements GlobalFilter,Ordered {

    /**
     *过滤器的规则
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        log.info("************* MyLogGateWayFilter welcome");
        String name = exchange.getRequest().getQueryParams().getFirst("uname");
        if(name == null){
            log.info("******* 用户名为null，非法用户禁止访问");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    /**
     * 确定filter的优先级
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
