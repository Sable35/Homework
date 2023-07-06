package com.example.webapplicationexample.repositories;

import com.example.webapplicationexample.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client getClientByLoginAndPassword(String login, String password);
}
