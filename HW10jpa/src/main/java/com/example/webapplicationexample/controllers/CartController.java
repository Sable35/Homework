package com.example.webapplicationexample.controllers;

import com.example.webapplicationexample.entities.Product;
import com.example.webapplicationexample.services.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("carts")
public class CartController {

    private final CartService cartService;
    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @DeleteMapping("/{idClient}/{idProduct}")
    public ResponseEntity<?> deleteProduct(@PathVariable long idProduct, @PathVariable long idClient) {
        log.info("Удаление продукта по idClient {} idProduct {}", idClient, idProduct);
        boolean isDeleted = cartService.deleteCartByProductAndClient(idClient, idProduct);

        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{idClient}")
    public ResponseEntity<?> updateProduct(@PathVariable long idClient,@RequestBody Product product) {
        log.info("обновления продукта {} по idClient {}", idClient, product.getName());
        boolean isUpdated = cartService.update(product, idClient);

        if (isUpdated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{idClient}")
    public ResponseEntity<?> addProduct(@PathVariable long idClient, @RequestBody Product product) {
        log.info("Добавление продукта с id{}", product.getId());
        boolean isAdded = cartService.addProductToCart(idClient, product);

        if (isAdded) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

}
