package com.example.webapplicationexample.controllers;

import com.example.webapplicationexample.services.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/{idUser}")
    public ResponseEntity<?> SumToPay(@PathVariable long idUser) {
        log.info("Подсчет суммы корзины по idUser {}", idUser);
        BigDecimal Sum = paymentService.cartSum(idUser);
        return ResponseEntity.ok().body(Sum);

    }
}
