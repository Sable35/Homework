package com.example.webapplicationexample.services;

import com.example.webapplicationexample.entities.User;
import com.example.webapplicationexample.entities.CutUser;

import java.util.Optional;

public interface UserService {
    Optional<User> findById(long id);
    long save(User user);
    boolean deleteById(long id);
    Optional<CutUser> getUserWithCart(long idUser);
}
