package com.example.webapplicationexample.repositories;

import com.example.webapplicationexample.entities.Product;
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
public class LocalProductRepository implements ProductRepository {
    private static final String JDBC = "jdbc:postgresql://localhost:5432/postgres?user=postgres&password=postgres";

    @Override
    public long save(Product product) {
        var insertSql = "INSERT INTO products_sobolev_ma.products (name, price) VALUES (?,?);";

        try (var connection = DriverManager.getConnection(JDBC);
             var prepareStatement = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
            prepareStatement.setString(1, product.getName());
            prepareStatement.setDouble(2, product.getPrice().doubleValue());

            prepareStatement.executeUpdate();

            ResultSet rs = prepareStatement.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new RuntimeException("Ошибка при получении идентификатора");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Product> findById(long idProduct) {
        var selectSql = "SELECT * FROM products_sobolev_ma.products where id = ?";

        try (var connection = DriverManager.getConnection(JDBC);
             var prepareStatement = connection.prepareStatement(selectSql)) {
            prepareStatement.setLong(1, idProduct);

            var resultSet = prepareStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                Product product = new Product(id, name,0, BigDecimal.valueOf(price));

                return Optional.of(product);
            }

            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> findAll(String nameProduct) {
        var selectSql = "SELECT * FROM products_sobolev_ma.products where name like ?";
        List<Product> products = new ArrayList<>();

        try (var connection = DriverManager.getConnection(JDBC);
             var prepareStatement = connection.prepareStatement(selectSql)) {
            prepareStatement.setString(1, "%" + (nameProduct == null ? "" : nameProduct) + "%");

            var resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                Product product = new Product(id, name,0, BigDecimal.valueOf(price));

                products.add(product);
            }

            return products;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Product product) {
        var selectSql = """
                UPDATE products_sobolev_ma.products
                SET 
                name = ?,
                price = ?
                where id = ?;
                """;

        try (var connection = DriverManager.getConnection(JDBC);
             var prepareStatement = connection.prepareStatement(selectSql)) {
            prepareStatement.setString(1, product.getName());
            prepareStatement.setDouble(2, product.getPrice().doubleValue());
            prepareStatement.setLong(3, product.getId());

            var rows = prepareStatement.executeUpdate();

            return rows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean deleteById(long idProduct) {
        var selectSql = "DELETE FROM products_sobolev_ma.products where id = ?";

        try (var connection = DriverManager.getConnection(JDBC);
             var prepareStatement = connection.prepareStatement(selectSql)) {
            prepareStatement.setLong(1, idProduct);

            var rows = prepareStatement.executeUpdate();

            return rows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
