package com.ritam.product.service.service;

import com.ritam.product.service.entities.Product;
import com.ritam.product.service.exception.ResourceNotFoundException;
import com.ritam.product.service.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService implements IProductService{
    @Autowired
    private IProductRepository productRepository;
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(String productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product Not found with id " + productId));
    }

    @Override
    public Product createProduct(Product product) {
        String randomUid = UUID.randomUUID().toString();
        product.setProductId(randomUid);
        return productRepository.save(product);
    }
}
