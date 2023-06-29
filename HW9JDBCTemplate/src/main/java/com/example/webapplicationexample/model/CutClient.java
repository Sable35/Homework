package com.example.webapplicationexample.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CutClient {
    private String name;
    private String email;
    private Cart cart;
}
