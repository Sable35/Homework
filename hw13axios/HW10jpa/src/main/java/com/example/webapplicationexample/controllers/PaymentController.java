package com.example.webapplicationexample.controllers;

import com.example.webapplicationexample.services.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Slf4j
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/{idClient}")
    public ResponseEntity<?> SumToPay(@PathVariable long idClient) {
        log.info("Подсчет суммы корзины по idClient {}", idClient);
        BigDecimal Sum = paymentService.cartSum(idClient);
        return ResponseEntity.ok().body(Sum);

    }
}
