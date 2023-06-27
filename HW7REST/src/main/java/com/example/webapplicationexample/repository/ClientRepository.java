package com.example.webapplicationexample.repository;

import com.example.webapplicationexample.model.Client;

import java.util.Optional;

public interface ClientRepository {

    public Optional<Client> findById(long id);
    long save(Client client);
    boolean deleteById(long id);
}
