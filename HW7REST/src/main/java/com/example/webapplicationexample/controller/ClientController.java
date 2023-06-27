package com.example.webapplicationexample.controller;

import com.example.webapplicationexample.model.Client;
import com.example.webapplicationexample.model.CutClient;
import com.example.webapplicationexample.model.Product;
import com.example.webapplicationexample.repository.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("clients")
public class ClientController {

    private ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @PostMapping
    public long registerClient(@RequestBody Client client) {
        log.info("Регистрация клиента {}", client);

        return clientRepository.save(client);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CutClient> getClient(@PathVariable long id) {
        log.info("Получение клиента по id {}", id);
        Optional<Client> client = clientRepository.findById(id);

        if (client.isPresent()) {
            CutClient cutClient = new CutClient(client.get().getName(),client.get().getEmail(), client.get().getCart());
            return ResponseEntity.ok().body(cutClient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable long id) {
        log.info("Удаление клиента по id {}", id);
        boolean isDeleted = clientRepository.deleteById(id);

        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
