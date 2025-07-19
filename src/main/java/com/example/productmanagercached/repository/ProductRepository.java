package com.example.productmanagercached.repository;

import com.example.productmanagercached.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
