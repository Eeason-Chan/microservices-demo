package com.eason.product.service.impl;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import com.eason.product.Product;
import com.eason.product.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public Product getProductById(Long id) {
        Product product = new Product();
        product.setId(0L);
        product.setPrice(new BigDecimal("100"));
        product.setProductName("测试商品");
        product.setNum(0);


//        try {
//            TimeUnit.SECONDS.sleep(2);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        return product;
    }
}
