package com.ritam.product.service.repository;

import com.ritam.product.service.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product,String> {
}
