package com.example.webapplicationexample.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class PaymentServiceImpl implements PaymentService{

    private final CartService cartService;

    public PaymentServiceImpl(CartService cartService) {
        this.cartService = cartService;
    }
    @Transactional
    public BigDecimal cartSum(long idUser){
        return cartService.sumOfCartUser(idUser);
    }
}
