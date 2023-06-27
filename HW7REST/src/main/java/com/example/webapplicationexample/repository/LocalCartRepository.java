package com.example.webapplicationexample.repository;

import com.example.webapplicationexample.model.Cart;
import com.example.webapplicationexample.model.Product;
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
        if (carts.contains(findById(idCart)) && productRepository.findById(idProduct).isPresent()) {
            carts.stream().filter(cart -> cart.getId() == idCart)
                    .findAny().get()
                    .getProductList().add(productRepository.findById(idProduct).get());
            return true;
        } else return false;
    }
    @Override
    public boolean deleteById(long idCart, long idProduct) {
        if (carts.contains(findById(idCart) ) ){
            return carts.get(carts.indexOf(findById(idCart))).getProductList().removeIf(product -> product.getId() == idProduct);
        } return false;
    }
    @Override
    public boolean update(Product product, long idCart) {
            if (carts.contains(findById(idCart)) && productRepository.findById(product.getId()).isPresent()) {
                carts.get(carts.indexOf(findById(idCart))).getProductList().get(carts.get(carts.indexOf(findById(idCart))).getProductList().indexOf(product)).setName(product.getName());
                carts.get(carts.indexOf(findById(idCart))).getProductList().get(carts.get(carts.indexOf(findById(idCart))).getProductList().indexOf(product)).setPrice(product.getPrice());
                return true;
            }
        return false;
    }

    public Optional<Cart> findById(long id) {
        return carts.stream()
                .filter(cart -> cart.getId() == id)
                .findAny();
    }


}
