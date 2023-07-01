package com.example.webapplicationexample.services;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentServiceImpl implements PaymentService{

    private final CartService cartService;

    public PaymentServiceImpl(CartService cartService) {
        this.cartService = cartService;
    }

    public BigDecimal cartSum(long idClient){
        return cartService.sumOfCartClient(idClient).get();
    }
}
