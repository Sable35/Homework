package com.example.webapplicationexample.services;

import com.example.webapplicationexample.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
@Service
public class LocalPaymentService implements PaymentService{

    private CartRepository cartRepository;

    public LocalPaymentService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }
    @Override
    public boolean cartSum(long idCart) {
        if (cartRepository.findById(idCart).isPresent()) {
            cartRepository.findById(idCart).get().getProductList().stream().map(product -> product.getPrice().multiply(BigDecimal.valueOf(product.getAmount()))).reduce(BigDecimal.ZERO,BigDecimal::add);
            return true;
        } else return false;
    }
}
