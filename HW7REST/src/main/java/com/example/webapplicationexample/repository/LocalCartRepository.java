package com.example.webapplicationexample.repository;

import com.example.webapplicationexample.model.Cart;
import com.example.webapplicationexample.model.Product;

import ch.qos.logback.core.joran.conditional.IfAction;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository
public class LocalCartRepository implements CartRepository{

    private static final String JDBC = "jdbc:postgresql://localhost:5432/postgres?user=postgres&password=postgres";
    @Override
    public boolean addProductToCart(long idClient,Product product){
        var insertSql = "INSERT INTO products_sobolev_ma.products_carts (id_product, id_client, amount) VALUES (?,?,?);";

        try (var connection = DriverManager.getConnection(JDBC);
             var prepareStatement = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
            prepareStatement.setLong(1, product.getId());
            prepareStatement.setLong(2, idClient);
            prepareStatement.setInt(3, product.getAmount());

            prepareStatement.executeUpdate();

            ResultSet rs = prepareStatement.getGeneratedKeys();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public boolean deleteById(long idClient, long idProduct) {
        var selectSql = "DELETE FROM products_sobolev_ma.products_carts where id_client = ? and id_product = ?";

        try (var connection = DriverManager.getConnection(JDBC);
             var prepareStatement = connection.prepareStatement(selectSql)) {
            prepareStatement.setLong(1, idClient);
            prepareStatement.setLong(2, idProduct);

            var rows = prepareStatement.executeUpdate();

            return rows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public boolean update(Product product, long idClient) {
        var selectSql = """
                UPDATE products_sobolev_ma.products_carts
                SET 
                amount = ?
                where id_product = ? and id_client = ?;
                """;

        try (var connection = DriverManager.getConnection(JDBC);
             var prepareStatement = connection.prepareStatement(selectSql)) {
            prepareStatement.setInt(1, product.getAmount());
            prepareStatement.setLong(2, product.getId());
            prepareStatement.setLong(3, idClient);

            var rows = prepareStatement.executeUpdate();

            return rows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
