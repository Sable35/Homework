package com.example.webapplicationexample.services;

import com.example.webapplicationexample.entities.Cart;
import com.example.webapplicationexample.entities.Client;
import com.example.webapplicationexample.entities.Product;
import com.example.webapplicationexample.repositories.CartRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ClientService clientService;
    private final ProductService productService;
    @Autowired
    public CartServiceImpl(CartRepository cartRepository, ClientService clientService, ProductService productService) {
        this.cartRepository = cartRepository;
        this.clientService = clientService;
        this.productService = productService;
    }

    @Override
    public Optional<Cart> findById(long idClient) {
        Optional<Cart> cart = cartRepository.findById(idClient);
        if (cart.isPresent()){
            return cart;
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Не существует такого товара в корзине");
    }

    @Override
    @Transactional
    public boolean deleteCartByProductAndClient(long idProduct, long idClient) {
        Optional<Client> client = clientService.findById(idClient);
        if (client.isPresent()){
            Optional<Product> product = productService.findById(idProduct);
            if (product.isEmpty()){
                cartRepository.deleteByProductAndClient(product.get(), client.get());
                return true;
            } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Не существует такого продукта");
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Не существует такого пользователя");
    }

    @Override
    public long save(Cart cart) {
        Cart savedCart = cartRepository.save(cart);
        return savedCart.getId();
    }

    public boolean addProductToCart(long idClient, Product product) {
        Optional<Client> client = clientService.findById(idClient);
        Optional<Cart> cart = cartRepository.findCartByProductAndClient(product, client.get());
            if (cart.isEmpty()) {
                Cart insertCart = new Cart();
                insertCart.setClient(client.get());
                insertCart.setProduct(product);
                insertCart.setAmount(product.getAmount());
                this.save(insertCart);
                return true;
            } else return false;
    }

    @Override
    public boolean update(Product product, long idClient) {
        Optional<Client> client = clientService.findById(idClient);
        Optional<Cart> cart = cartRepository.findCartByProductAndClient(product, client.get());
        cart.get().setAmount(product.getAmount());
        cartRepository.save(cart.get());
        return true;
    }
    @Override
    public boolean reduceProduct(Cart cart){
        Product product = cart.getProduct();
        product.setAmount(product.getAmount()-cart.getAmount());
        productService.update(product);
        return true;
    }

    @Override
    public Optional<BigDecimal> sumOfCartClient(long idClient) {
        Optional<Client> client = clientService.findById(idClient);
        BigDecimal sum = BigDecimal.valueOf(0);
            for (Cart cart : cartRepository.findCartsByClient(client.get())) {
                if (isAmountProductEnough(cart)) {
                        sum = sum.add(BigDecimal.valueOf
                                (cart.getAmount()).
                                multiply(cart.getProduct().getPrice()));
                        reduceProduct(cart);
                }
            }
        cartRepository.deleteAllByClient(client.get());
        return Optional.of(sum);
    }

    public boolean isAmountProductEnough(Cart cart) {
        if (cart.getAmount() < cart.getProduct().getAmount()){
            return true;
        } else 
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Недостаточно товара на складе");
    }
}