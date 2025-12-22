package com.eason.order.service.impl;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.eason.order.Order;
import com.eason.order.feign.ProductFeignClient;
import com.eason.order.service.OrderService;
import com.eason.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    ProductFeignClient productFeignClient;

    @SentinelResource(value = "createOrder", blockHandler = "createOrderFallback")
    @Override
    public Order createOrder(Long productId, Long userId) {
//        Product product = getProductBalancedAnnotation(productId);
//        Product product = getProductByRemote(productId);
//        Product product = productFeignClient.getProductById(productId);
        Product product = productFeignClient.getProductById(productId);

        Order order = new Order();
        order.setId(1L);
        // 总金额
        order.setTotalAmount(product.getPrice().multiply(new BigDecimal(product.getNum())));
        order.setUserId(userId);
        order.setNickName("小明");
        order.setAddress("成都");
        // 商品列表
        order.setProducts(List.of(product));
        return order;
    }

    private Product getProductByRemote(Long productId){
        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
        ServiceInstance instance = instances.get(0);
        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/getProduct/" + productId;
        Product product = restTemplate.getForObject(url, Product.class);
        return product;
    }

    private Product getProductByRemoteWithLoadBalancer(Long productId){
//        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
//        ServiceInstance instance = loadBalancerClient.choose("service-product");
//        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/getProduct/" + productId;

        String url = "http://service-product/getProduct/" + productId;
        Product product = restTemplate.getForObject(url, Product.class);
        return product;
    }

    // 兜底回调
    public Order createOrderFallback(Long productId, Long userId, BlockException e){
        Order order = new Order();
        order.setId(00L);
        order.setTotalAmount(BigDecimal.valueOf(00));
        order.setUserId(userId);
        order.setNickName("兜底数据");
        order.setAddress("兜底数据"+e.getClass());
        order.setProducts(null);
        return order;
    }
}
