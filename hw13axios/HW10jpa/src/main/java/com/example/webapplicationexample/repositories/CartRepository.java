package com.example.webapplicationexample.repositories;

import com.example.webapplicationexample.entities.Cart;
import com.example.webapplicationexample.entities.Client;
import com.example.webapplicationexample.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    int deleteByProductAndClient(Product product, Client client);

    List<Cart> findCartsByClient(Client client);

    Optional<Cart> findCartByProductAndClient(Product product, Client client);

    boolean deleteAllByClient(Client client);
}
