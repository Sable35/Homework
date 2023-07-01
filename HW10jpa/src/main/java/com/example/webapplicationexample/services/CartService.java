package com.example.webapplicationexample.services;

import com.example.webapplicationexample.entities.Cart;
import com.example.webapplicationexample.entities.Product;

import java.math.BigDecimal;
import java.util.Optional;

public interface CartService {
    Optional<Cart> findById(long idClient);
    boolean deleteCartByProductAndClient(long idProduct,long idClient);

    Optional<BigDecimal> sumOfCartClient(long idClient);

    boolean isAmountProductEnough(Cart cartList);
    long save(Cart cart);
    boolean update(Product product, long idClient);
    boolean addProductToCart(long idClient,Product product);

    boolean reduceProduct(Cart cart);
    boolean deleteAll();
}
