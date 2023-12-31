package com.example.webapplicationexample.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Client {
    private long id;
    private String name;
    private String login;
    private String password;
    private String email;
    private Cart cart;
}
