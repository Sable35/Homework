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

    @DeleteMapping("/{idCart}/{idProduct}")
    public ResponseEntity<?> deleteProduct(@PathVariable long idProduct, @PathVariable long idCart) {
        log.info("Удаление клиента по idCart {} idProduct {}", idCart, idProduct);
        boolean isDeleted = cartRepository.deleteById(idCart, idProduct);

        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{idCart}")
    public ResponseEntity<?> updateProduct(@PathVariable long idCart,@RequestBody Product product) {
        log.info("обновления продукта {} по idCart {}", idCart, product.getName());
        boolean isUpdated = cartRepository.update(product, idCart);

        if (isUpdated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{idCart}/{idProduct}")
    public ResponseEntity<?> addProduct(@PathVariable long idCart, @PathVariable long idProduct) {
        log.info("Добавление продукта по idProduct{}", idProduct);
        boolean isAdded = cartRepository.addProductToCart(idCart,idProduct);

        if (isAdded) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
