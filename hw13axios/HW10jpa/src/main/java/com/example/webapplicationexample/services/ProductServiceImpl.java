package com.example.webapplicationexample.services;


import com.example.webapplicationexample.entities.Product;
import com.example.webapplicationexample.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public long save(Product product) {
        Product savedProduct = productRepository.save(product);
        return savedProduct.getId();
    }

    @Override
    public Product findById(long idProduct) {
        Optional<Product> product = productRepository.findById(idProduct);
        if (product.isPresent()){
            return product.get();
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Не существует такого продукта");
    }

    @Override
    public List<Product> findByName(String name) {
        return productRepository.findAllByNameContainingIgnoreCase(name);
    }

    @Override
    public boolean update(Product product) {
        productRepository.save(product);
        return true;
    }

    @Override
    public boolean deleteById(long id) {
        productRepository.deleteById(id);
        return true;
    }
}
