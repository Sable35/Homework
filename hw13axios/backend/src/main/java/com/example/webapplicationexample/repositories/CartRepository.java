package com.example.webapplicationexample.repositories;

import com.example.webapplicationexample.entities.Cart;
import com.example.webapplicationexample.entities.User;
import com.example.webapplicationexample.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    int deleteByProductAndUser(Product product, User user);

    List<Cart> findCartsByUser(User user);

    Optional<Cart> findCartByProductAndUser(Product product, User user);

    boolean deleteAllByUser(User user);
}
