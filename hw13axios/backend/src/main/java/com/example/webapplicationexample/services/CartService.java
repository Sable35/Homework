package com.example.webapplicationexample.services;

import com.example.webapplicationexample.entities.Cart;
import com.example.webapplicationexample.entities.Product;
import com.example.webapplicationexample.entities.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CartService {
    Optional<Cart> findById(long idUser);

    boolean deleteById(long id);

    boolean deleteCartByProductAndUser(long idProduct, long idUser);

    BigDecimal sumOfCartUser(long idUser);

    List<Product> getProductsInCart(long idUser);

    boolean isAmountProductEnough(Cart cartList);
    long save(Cart cart);
    boolean update(Product product, long idCart);
    boolean addProductToCart(long idUser,Product product);
    boolean reduceProduct(Cart cart);


    Optional<Cart> findCartByProductAndUser(Product product, User user);

    List<Cart> findCartsByIdUser(long idUser);
}
