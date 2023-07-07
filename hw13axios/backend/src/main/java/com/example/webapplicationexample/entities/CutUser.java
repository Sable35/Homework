package com.example.webapplicationexample.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CutUser {
    private String name;
    private String email;
    List<Cart> cartList;
}
