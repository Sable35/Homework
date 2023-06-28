package com.example.webapplicationexample.repository;

import com.example.webapplicationexample.model.Cart;
import com.example.webapplicationexample.model.Product;

import java.util.Optional;

public interface CartRepository {

    boolean deleteById(long idCart, long idProduct);
    public boolean addProductToCart(long idClient,Product product);
    boolean update(Product product, long idCart);
}
