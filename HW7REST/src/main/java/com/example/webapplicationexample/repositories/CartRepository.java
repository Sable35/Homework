package com.example.webapplicationexample.repositories;

import com.example.webapplicationexample.entities.Product;

public interface CartRepository {

    boolean deleteById(long idCart, long idProduct);
    public boolean addProductToCart(long idClient,Product product);
    boolean update(Product product, long idCart);
}
