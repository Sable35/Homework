package com.example.webapplicationexample.services;

import com.example.webapplicationexample.entities.Client;
import com.example.webapplicationexample.entities.CutClient;
import com.example.webapplicationexample.repositories.CartRepository;
import com.example.webapplicationexample.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
@Service
public class ClientServiceImpl implements ClientService{

    private final ClientRepository clientRepository;
    private final CartRepository cartRepository;
    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, CartRepository cartRepository) {
        this.clientRepository = clientRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    public Optional<Client> findById(long id){

        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()){
            return client;
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Не существует такого пользователя");
    }

    public Optional<CutClient> getClientWithCart(long idClient){
        Optional<Client> client = this.findById(idClient);
        return Optional.of(new CutClient(client.get().getName(),client.get().getEmail(),cartRepository.findCartsByClient(client.get())));
    }
    @Override
    public long save(Client client){
        Client savedClient = clientRepository.save(client);
        return savedClient.getId();
    }
    @Override
    public boolean deleteById(long id){
        clientRepository.deleteById(id);
        return true;
    }
}
