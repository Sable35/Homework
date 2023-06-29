package com.example.webapplicationexample.repository;

import com.example.webapplicationexample.model.Client;
import com.example.webapplicationexample.model.CutClient;

import java.util.Optional;

public interface ClientRepository {

    public Optional<Client> findById(long idClient);
    long save(Client client);
    boolean deleteById(long id);
}
