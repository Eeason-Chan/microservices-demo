package com.eason.order.feign;

import com.eason.order.feign.fallback.ProductFallback;
import com.eason.product.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "service-product", contextId = "productFeignClient", fallback = ProductFallback.class)
public interface ProductFeignClient {

    @GetMapping("/getProduct/{id}")
    Product getProductById(@PathVariable("id") Long id);
}
