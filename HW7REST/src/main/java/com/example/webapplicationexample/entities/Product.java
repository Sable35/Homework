package com.example.webapplicationexample.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Product {
    private long id;
    private String name;
    private int amount;
    private BigDecimal price;
}
