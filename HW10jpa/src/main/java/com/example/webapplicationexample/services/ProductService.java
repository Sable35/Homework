package com.example.webapplicationexample.services;

import com.example.webapplicationexample.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    long save(Product product);

    Optional<Product> findById(long idProduct);

    List<Product> findByName(String name);

    boolean update(Product product);

    boolean deleteById(long id);
}
