package com.example.webapplicationexample.entities;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
public class CutProduct {
    private long id;

    private String name;

    private int amount;

    private BigDecimal price;
}
