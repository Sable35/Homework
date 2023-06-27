package com.example.webapplicationexample.repository;

import com.example.webapplicationexample.model.Cart;
import com.example.webapplicationexample.model.Client;
import com.example.webapplicationexample.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
@Repository
public class LocalClientRepository implements ClientRepository{


    private CartRepository cartRepository;

    public LocalClientRepository(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    private List<Client> clients = new ArrayList<>(List.of(
    ));



    @Override
    public long save(Client client) {
        long id = generateId();
        client.setId(id);
        Cart cart = new Cart(id,new ArrayList<Product>(),generateId());
        cartRepository.addCart(cart);
        client.setCart(cart);

        clients.add(client);
        return id;
    }

    @Override
    public Optional<Client> findById(long id) {
        return clients.stream()
                .filter(product -> product.getId() == id)
                .findAny();
    }

    @Override
    public boolean deleteById(long id) {
        return clients.removeIf(client -> client.getId() == id);
    }

    private long generateId() {
        Random random = new Random();
        int low = 1;
        int high = 1_000_000;
        return random.nextLong(high - low) + low;
    }
}
