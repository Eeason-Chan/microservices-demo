package com.eason.product.service;


import com.eason.product.Product;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {

    Product getProductById(Long id);
}