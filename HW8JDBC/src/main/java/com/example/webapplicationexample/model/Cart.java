package com.example.webapplicationexample.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Cart {
    private List<Product> productList;
    private String promoCode;
}
