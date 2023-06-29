package com.example.webapplicationexample.repository;

import com.example.webapplicationexample.model.Cart;
import com.example.webapplicationexample.model.Product;

import java.util.List;
import java.util.Optional;

public interface CartRepository {
    List<Product> findById(long idClient);
    boolean deleteById(long idCart, long idProduct);
    public boolean addProductToCart(long idClient,Product product);
    boolean update(Product product, long idCart);
}
