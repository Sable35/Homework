package com.example.webapplicationexample.services;

import com.example.webapplicationexample.entities.Client;
import com.example.webapplicationexample.entities.CutClient;

import java.util.Optional;

public interface ClientService {
    Optional<Client> findById(long id);
    long save(Client client);
    boolean deleteById(long id);
    Optional<CutClient> getClientWithCart(long idClient);

    Client getClientByLoginAndPassword(String login, String password);
}
