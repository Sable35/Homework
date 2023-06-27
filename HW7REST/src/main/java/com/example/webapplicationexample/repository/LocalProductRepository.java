package com.example.webapplicationexample.repository;

import com.example.webapplicationexample.model.Product;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Repository
public class LocalProductRepository implements ProductRepository {
    private List<Product> products = new ArrayList<>(List.of(
    ));

    @Override
    public long save(Product product) {
        long id = generateId();
        product.setId(id);

        products.add(product);
        return id;
    }

    @Override
    public Optional<Product> findById(long id) {
        return products.stream()
                .filter(product -> product.getId() == id)
                .findAny();
    }

    @Override
    public List<Product> findAll(String name) {
        if (name == null) {
            return products;
        }

        return products.stream()
                .filter(product -> product.getName().equals(name))
                .toList();
    }

    @Override
    public boolean update(Product product) {
        for (Product p : products) {
            if (p.getId() == product.getId()) {
                p.setName(product.getName());
                p.setPrice(product.getPrice());

                return true;
            }
        }

        return false;
    }

    @Override
    public boolean deleteById(long id) {
        return products.removeIf(product -> product.getId() == id);
    }

    private long generateId() {
        Random random = new Random();
        int low = 1;
        int high = 1_000_000;
        return random.nextLong(high - low) + low;
    }
}
