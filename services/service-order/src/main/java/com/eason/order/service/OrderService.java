package com.eason.order.service;


import com.eason.order.Order;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    Order createOrder(Long product, Long userId);
}