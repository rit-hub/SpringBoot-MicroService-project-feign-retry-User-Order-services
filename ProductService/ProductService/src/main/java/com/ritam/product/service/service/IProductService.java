package com.ritam.product.service.service;

import com.ritam.product.service.entities.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAllProducts();
    Product getProduct(String productId);
    Product createProduct(Product product);
}
