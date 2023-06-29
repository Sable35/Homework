package com.example.webapplicationexample.repository;

import com.example.webapplicationexample.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
public class DBProductRepository implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DBProductRepository(JdbcTemplate jdbcTemplate) {
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
    public long save(Product product) {
        var insertSql = "INSERT INTO products (name, price, amount) VALUES (?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        PreparedStatementCreator preparedStatementCreator = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice().doubleValue());
            preparedStatement.setInt(3, product.getAmount());

            return preparedStatement;
        };

        jdbcTemplate.update(preparedStatementCreator, keyHolder);

        return (long) (int) keyHolder.getKeys().get("id");
    }

    @Override
    public Optional<Product> findById(long idProduct) {
        var selectSql = "SELECT * FROM products where id = ?";
        PreparedStatementCreator preparedStatementCreator = connection -> {
            var prepareStatement = connection.prepareStatement(selectSql);
            prepareStatement.setLong(1, idProduct);

            return prepareStatement;
        };

        RowMapper<Product> productRowMapper = getProductRowMapper();

        List<Product> products = jdbcTemplate.query(preparedStatementCreator, productRowMapper);

        return products.stream().findFirst();
    }

    @Override
    public List<Product> findAll(String nameProduct) {
        var selectSql = "SELECT * FROM products where name like ?";
        PreparedStatementCreator preparedStatementCreator = connection -> {
            var prepareStatement = connection.prepareStatement(selectSql);
            prepareStatement.setString(1, "%" + (nameProduct == null ? "" : nameProduct) + "%");

            return prepareStatement;
        };

        RowMapper<Product> productRowMapper = getProductRowMapper();

        return jdbcTemplate.query(preparedStatementCreator, productRowMapper);
    }

    @Override
    public boolean update(Product product) {
        var updateSql = """
                UPDATE products
                SET 
                name = ?,
                price = ?,
                amount = ?
                where id = ?;
                """;

        PreparedStatementCreator preparedStatementCreator = connection -> {
            var prepareStatement = connection.prepareStatement(updateSql);
            prepareStatement.setString(1, product.getName());
            prepareStatement.setDouble(2, product.getPrice().doubleValue());
            prepareStatement.setInt(3, product.getAmount());
            prepareStatement.setLong(4, product.getId());

            return prepareStatement;
        };

        int rows = jdbcTemplate.update(preparedStatementCreator);

        return rows > 0;

    }

    @Override
    public boolean deleteById(long idProduct) {
        var deleteSql = "DELETE FROM products where id = ?";

        PreparedStatementCreator preparedStatementCreator = connection -> {
            var prepareStatement = connection.prepareStatement(deleteSql);
            prepareStatement.setLong(1, idProduct);

            return prepareStatement;
        };

        int rows = jdbcTemplate.update(preparedStatementCreator);

        return rows > 0;
    }
}
