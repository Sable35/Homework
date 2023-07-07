package com.example.webapplicationexample.services;

import com.example.webapplicationexample.entities.User;
import com.example.webapplicationexample.entities.CutUser;
import com.example.webapplicationexample.repositories.CartRepository;
import com.example.webapplicationexample.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, CartRepository cartRepository) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    public Optional<User> findById(long id){

        Optional<User> client = userRepository.findById(id);
        if (client.isPresent()){
            return client;
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Не существует такого пользователя");
    }

    public Optional<CutUser> getUserWithCart(long idUser){
        Optional<User> client = findById(idUser);
        return Optional.of(new CutUser(client.get().getUsername(),client.get().getEmail(),cartRepository.findCartsByUser(client.get())));
    }

    @Override
    public long save(User user){
        User savedUser = userRepository.save(user);
        return savedUser.getId();
    }
    @Override
    public boolean deleteById(long id){
        userRepository.deleteById(id);
        return true;
    }
}
