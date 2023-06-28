package com.example.webapplicationexample.repository;

import com.example.webapplicationexample.model.Cart;
import com.example.webapplicationexample.model.Product;

import ch.qos.logback.core.joran.conditional.IfAction;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository
public class LocalCartRepository implements CartRepository{

    private ProductRepository productRepository;

    public LocalCartRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private List<Cart> carts= new ArrayList<>(List.of(
    ));

    @Override
    public void addCart(Cart cart){
        carts.add(cart);
    }
    @Override
    public boolean addProductToCart(long idCart,long idProduct){
        if (productRepository.findById(idProduct).isPresent()) {
            Optional<Cart> cart = carts.stream().filter(cartt -> cartt.getId() == idCart).findAny();
                    if (cart.isPresent()) {
                        cart.get().getProductList().add(productRepository.findById(idProduct).get());
                        return true;
                    }
    
        } 
        return false;
    }
    @Override
    public boolean deleteById(long idCart, long idProduct) {
        Optional<Cart> cart = carts.stream().filter(cartt -> cartt.getId() == idCart).findAny();
        if (cart.isPresent()){
            return cart.get().getProductList().removeIf(product -> product.getId() == idProduct);
        } return false;
    }
    @Override
    public boolean update(Product product, long idCart) {
            if (productRepository.findById(product.getId()).isPresent()) {
                Optional<Cart> cart = carts.stream().filter(cartt -> cartt.getId() == idCart).findAny();
                if (cart.isPresent()) {
                        cart.get().getProductList().get(cart.get().getProductList().indexOf(productRepository.findById(product.getId()).get())).setName(product.getName());
                        cart.get().getProductList().get(cart.get().getProductList().indexOf(productRepository.findById(product.getId()).get())).setPrice(product.getPrice());
                        return true;
                    }
            }
        return false;
    }

    public Optional<Cart> findById(long id) {
        return carts.stream()
                .filter(cart -> cart.getId() == id)
                .findAny();
    }


}
