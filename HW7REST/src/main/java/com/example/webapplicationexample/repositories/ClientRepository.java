package com.example.webapplicationexample.repositories;

import com.example.webapplicationexample.entities.Client;

import java.util.Optional;

public interface ClientRepository {

    public Optional<Client> findById(long idClient);
    long save(Client client);
    boolean deleteById(long id);
}
