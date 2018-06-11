package com.app.repository;

import com.app.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    void addProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(Integer id);
    Optional<Product> findOneById(Integer id);
    Optional<Product> findOneByName(String name);
    List<Product> findAll();
}
