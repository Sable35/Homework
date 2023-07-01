package com.example.webapplicationexample.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CutClient {
    private String name;
    private String email;
    private Cart cart;
}
