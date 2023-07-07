package com.example.webapplicationexample.controllers;

import com.example.webapplicationexample.entities.Cart;
import com.example.webapplicationexample.entities.Product;
import com.example.webapplicationexample.services.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("carts")
public class CartController {

    private final CartService cartService;
    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{idUser}")
    public ResponseEntity<List<Product>> getCartsByIdUser(@PathVariable long idUser) {
        log.info("получение корзины клиента по id клиента {}", idUser);
        List<Product> carts = cartService.getProductsInCart(idUser);
        return ResponseEntity.ok().body(carts);
    }
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable long id) {
        log.info("Удаление продукта по id {}", id);
        boolean isDeleted = cartService.deleteById(id);

        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable long id,@RequestBody Product product) {
        log.info("обновления продукта c id {} по id пользователя {}", product.getId(), id);
        boolean isUpdated = cartService.update(product, id);

        if (isUpdated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/{idUser}")
    public ResponseEntity<?> addProduct(@PathVariable long idUser, @RequestBody Product product) {
        log.info("Добавление продукта с id{}", product.getId());
        boolean isAdded = cartService.addProductToCart(idUser, product);

        if (isAdded) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

}
