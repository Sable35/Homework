package com.example.webapplicationexample.repositories;

import com.example.webapplicationexample.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameIsLike(String name);
}
