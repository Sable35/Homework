package com.example.webapplicationexample.repository;

import com.example.webapplicationexample.model.Cart;
import com.example.webapplicationexample.model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class DBCartRepository implements CartRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DBCartRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static RowMapper<Product> getProductRowMapper() {
        return (resultSet, rowNum) -> {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int amount = resultSet.getInt("amount");
            double price = resultSet.getDouble("price");
            return new Product(id, name, amount, BigDecimal.valueOf(price));
        };
    }
    @Override
    public boolean addProductToCart(long idClient,Product product){
        var insertSql = "INSERT INTO products_clients (id_product, id_client, amount) VALUES (?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        PreparedStatementCreator preparedStatementCreator = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, product.getId());
            preparedStatement.setLong(2, idClient);
            preparedStatement.setInt(3, product.getAmount());

            return preparedStatement;
        };

        return jdbcTemplate.update(preparedStatementCreator, keyHolder) > 0;
    }

    @Override
    public List<Product> findById(long idClient) {
        var selectSql = """
                SELECT p.id, p.name, p.price, pc.amount
                FROM products_clients pc
                join products p on p.id=pc.id_product
                where id_client = ?""";
        PreparedStatementCreator preparedStatementCreator = connection -> {
            var prepareStatement = connection.prepareStatement(selectSql);
            prepareStatement.setLong(1, idClient);

            return prepareStatement;
        };

        RowMapper<Product> productRowMapper = getProductRowMapper();

        List<Product> products = jdbcTemplate.query(preparedStatementCreator, productRowMapper);

        return products;
    }


    @Override
    public boolean deleteById(long idClient, long idProduct) {
        var deleteSql = "DELETE FROM products_clients where id_client = ? and id_product = ?";

        PreparedStatementCreator preparedStatementCreator = connection -> {
            var prepareStatement = connection.prepareStatement(deleteSql);
            prepareStatement.setLong(1, idClient);
            prepareStatement.setLong(2, idProduct);

            return prepareStatement;
        };
        int rows = jdbcTemplate.update(preparedStatementCreator);

        return rows > 0;
    }
    @Override
    public boolean update(Product product, long idClient) {
        var updateSql = """
                UPDATE products_clients
                SET 
                amount = ?
                where id_product = ? and id_client = ?;
                """;

        PreparedStatementCreator preparedStatementCreator = connection -> {
            var prepareStatement = connection.prepareStatement(updateSql);
            prepareStatement.setInt(1, product.getAmount());
            prepareStatement.setDouble(2, product.getId());
            prepareStatement.setLong(3, idClient);

            return prepareStatement;
        };

        int rows = jdbcTemplate.update(preparedStatementCreator);

        return rows > 0;
    }
}
