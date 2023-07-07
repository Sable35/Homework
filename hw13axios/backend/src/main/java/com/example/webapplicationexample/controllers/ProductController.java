package com.example.webapplicationexample.controllers;

import com.example.webapplicationexample.entities.Product;
import com.example.webapplicationexample.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("products")
public class ProductController {

    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public long addProduct(@RequestBody Product product) {
        log.info("Добавление продукта {}", product);

        return productService.save(product);
    }

    @GetMapping
    public  ResponseEntity<List<Product>> getProducts(@RequestParam(required = false) String name) {
        log.info("Поиск продуктов по имени {}", name);
        if (name == null) {
            return ResponseEntity.ok().body(productService.findByName(""));
        }
        return ResponseEntity.ok().body(productService.findByName(name));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public Product updateProduct(@RequestBody Product product) {
        log.info("Обновление продукта");
        productService.update(product);

        return product;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable long id) {
        log.info("Удаление продукта по id {}", id);
        boolean isDeleted = productService.deleteById(id);

        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
