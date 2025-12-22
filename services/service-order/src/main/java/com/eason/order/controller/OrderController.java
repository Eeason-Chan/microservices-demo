package com.eason.order.controller;

import com.eason.order.Order;
import com.eason.order.property.OrderProperties;
import com.eason.order.service.OrderService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@RequestMapping("/api/order")
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderProperties orderProperties;

//    @Value("${order.timeout}")
//    String orderTimeout;

    @GetMapping("/config")
    public String config(){
        System.out.println("order.timeout: " + orderProperties.getTimeout());
        return orderProperties.getTimeout();
    }

    @GetMapping("/create")
    public Order createOrder(@RequestParam("userId") Long userId, @RequestParam("productId") Long productId) {
        return orderService.createOrder(productId, userId);
    }
}
