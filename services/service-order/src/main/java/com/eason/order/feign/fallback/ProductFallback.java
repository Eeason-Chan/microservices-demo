package com.eason.order.feign.fallback;

import com.eason.order.feign.ProductFeignClient;
import com.eason.product.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductFallback implements ProductFeignClient {
    @Override
    public Product getProductById(Long id) {
        // 兜底数据
        System.out.println("兜底回调");
        Product product = new Product();
        product.setId(id);
        product.setPrice(BigDecimal.valueOf(10.00));
        product.setProductName("兜底商品");
        product.setNum(0);
        return product;
    }
}
