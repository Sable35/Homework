package com.example.webapplicationexample.controllers;

import com.example.webapplicationexample.entities.Client;
import com.example.webapplicationexample.entities.CutClient;
import com.example.webapplicationexample.services.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public long registerClient(@RequestBody Client client) {
        log.info("Регистрация клиента {}", client);

        return clientService.save(client);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CutClient> getClient(@PathVariable long id) {
        log.info("Получение клиента по id {}", id);
        Optional<CutClient> clientWithCart = clientService.getClientWithCart(id);

        if (clientWithCart.isPresent()) {
            return ResponseEntity.ok().body(clientWithCart.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/{login}/{password}")
    public ResponseEntity<Client> getClient(@PathVariable String login,@PathVariable String password) {
        log.info("Получение клиента по логину {} и паролю {}", login, password);
        Client client = clientService.getClientByLoginAndPassword(login, password);

        if (client != null) {
            return ResponseEntity.ok().body(client);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable long id) {
        log.info("Удаление клиента по id {}", id);
        boolean isDeleted = clientService.deleteById(id);

        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
