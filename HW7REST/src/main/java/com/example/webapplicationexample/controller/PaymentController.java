package com.example.webapplicationexample.controller;

import com.example.webapplicationexample.services.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequestMapping("payments")
public class PaymentController {

    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/{idCart}")
    public ResponseEntity<?> SumToPay(@PathVariable long idCart) {
        log.info("Подсчет суммы корзины по idCart {}", idCart);
        boolean isSum = paymentService.cartSum(idCart);

        if (isSum) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
