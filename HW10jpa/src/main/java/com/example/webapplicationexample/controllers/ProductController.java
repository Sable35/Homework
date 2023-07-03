package com.example.webapplicationexample.controllers;

import com.example.webapplicationexample.entities.CutProduct;
import com.example.webapplicationexample.entities.Product;
import com.example.webapplicationexample.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("products")
public class ProductController {

    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public long addProduct(@RequestBody Product product) {
        log.info("Добавление продукта {}", product);

        return productService.save(product);
    }

    @GetMapping
    public List<Product> getProducts(@RequestParam(required = false) String name) {
        log.info("Поиск продуктов по имени {}", name);

        return productService.findByName(name);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CutProduct> getProducts(@PathVariable long id) {
        log.info("Получение продукта по id {}", id);
        CutProduct cutProduct = productService.findById(id);
        return ResponseEntity.ok().body(cutProduct);

    }

    @PutMapping
    public Product updateProduct(@RequestBody Product product) {
        log.info("Обновление продукта");
        productService.update(product);

        return product;
    }

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
