package com.example.webapplicationexample.services;

import com.example.webapplicationexample.entities.Cart;
import com.example.webapplicationexample.entities.User;
import com.example.webapplicationexample.entities.Product;
import com.example.webapplicationexample.repositories.CartRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final UserService userService;
    private final ProductService productService;
    @Autowired
    public CartServiceImpl(CartRepository cartRepository, UserService userService, ProductService productService) {
        this.cartRepository = cartRepository;
        this.userService = userService;
        this.productService = productService;
    }

    @Override
    public Optional<Cart> findById(long id) {
        Optional<Cart> cart = cartRepository.findById(id);
        if (cart.isPresent()){
            return cart;
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Не существует такого товара в корзине");
    }

    @Override
    public Optional<Cart> findCartByProductAndUser(Product product, User user) {
        Optional<Cart> cart = cartRepository.findCartByProductAndUser(product, user);
        System.out.println(cart);
        if (cart.isPresent()){
            return cart;
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Не существует такого товара в корзине");
    }

    public List<Cart> findCartsByIdUser(long idUser){
        Optional<User> user = userService.findById(idUser);
        return cartRepository.findCartsByUser(user.get());
    }
    @Override
    public boolean deleteById(long id) {
        cartRepository.deleteById(id);
        return true;
    }
    @Override
    @Transactional
    public boolean deleteCartByProductAndUser(long idProduct, long idUser) {
        Optional<User> user = userService.findById(idUser);
        if (user.isPresent()){
            Product product = productService.findById(idProduct);
            if (product != null){
                cartRepository.deleteByProductAndUser(product, user.get());
                return true;
            } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Не существует такого продукта");
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Не существует такого пользователя");
    }

    @Override
    public long save(Cart cart) {
        Cart savedCart = cartRepository.save(cart);
        return savedCart.getId();
    }

    public boolean addProductToCart(long idUser, Product product) {
        Optional<User> user = userService.findById(idUser);
        Optional<Cart> cart = cartRepository.findCartByProductAndUser(product, user.get());
            if (cart.isEmpty()) {
                Cart insertCart = new Cart();
                insertCart.setUser(user.get());
                insertCart.setProduct(product);
                insertCart.setAmount(product.getAmount());
                this.save(insertCart);
                return true;
            } else return false;
    }

    @Override
    public boolean update(Product product, long idCart) {
        Cart cart = findById(idCart).get();
        cart.setAmount(product.getAmount());
        cartRepository.save(cart);
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
    public BigDecimal sumOfCartUser(long idUser) {
        Optional<User> user = userService.findById(idUser);
        BigDecimal sum = BigDecimal.valueOf(0);
            for (Cart cart : cartRepository.findCartsByUser(user.get())) {
                if (isAmountProductEnough(cart)) {
                        sum = sum.add(BigDecimal.valueOf
                                (cart.getAmount()).
                                multiply(cart.getProduct().getPrice()));
                        reduceProduct(cart);
                }
            }
        cartRepository.deleteAllByUser(user.get());
        return sum;
    }
    @Override
    public List<Product> getProductsInCart(long idUser){
        User user = userService.findById(idUser).get();
        List<Cart> carts = cartRepository.findCartsByUser(user);
        List<Product> products = new ArrayList<>();
        for (Cart cart : carts) {
            Product product = new Product();
            product.setId(cart.getId());
            product.setAmount(cart.getAmount());
            product.setPrice(cart.getProduct().getPrice());
            product.setName(cart.getProduct().getName());
            products.add(product);
        }
        return products;
    }

    public boolean isAmountProductEnough(Cart cart) {
        if (cart.getAmount() < cart.getProduct().getAmount()){
            return true;
        } else 
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Недостаточно товара на складе");
    }
}