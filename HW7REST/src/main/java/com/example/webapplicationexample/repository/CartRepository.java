package com.example.webapplicationexample.repository;

import com.example.webapplicationexample.model.Cart;
import com.example.webapplicationexample.model.Product;

import java.util.Optional;

public interface CartRepository {
    void addCart(Cart cart);

    boolean deleteById(long idCart, long idProduct);
    boolean addProductToCart(long idCart,long idProduct);
    boolean update(Product product, long idCart);
    Optional<Cart> findById(long id);

}
