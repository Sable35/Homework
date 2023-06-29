package com.example.webapplicationexample.controller;

import com.example.webapplicationexample.model.Product;
import com.example.webapplicationexample.repository.CartRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("carts")
public class CartController {

    private CartRepository cartRepository;

    public CartController(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @DeleteMapping("/{idClient}/{idProduct}")
    public ResponseEntity<?> deleteProduct(@PathVariable long idProduct, @PathVariable long idClient) {
        log.info("Удаление продукта по idClient {} idProduct {}", idClient, idProduct);
        boolean isDeleted = cartRepository.deleteById(idClient, idProduct);

        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{idClient}")
    public ResponseEntity<?> updateProduct(@PathVariable long idClient,@RequestBody Product product) {
        log.info("обновления продукта {} по idClient {}", idClient, product.getName());
        boolean isUpdated = cartRepository.update(product, idClient);

        if (isUpdated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{idClient}")
    public ResponseEntity<?> addProduct(@PathVariable long idClient, @RequestBody Product product) {
        log.info("Добавление продукта ", product);
        boolean isAdded = cartRepository.addProductToCart(idClient, product);

        if (isAdded) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
