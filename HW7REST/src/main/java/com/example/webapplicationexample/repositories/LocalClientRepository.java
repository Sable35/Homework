package com.example.webapplicationexample.repositories;

import com.example.webapplicationexample.entities.Cart;
import com.example.webapplicationexample.entities.Client;
import com.example.webapplicationexample.entities.Product;
import org.springframework.stereotype.Repository;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public class LocalClientRepository implements ClientRepository{

    private static final String JDBC = "jdbc:postgresql://localhost:5432/postgres?user=postgres&password=postgres";

    @Override
    public long save(Client client) {
        var insertSql = "INSERT INTO products_sobolev_ma.clients (name, username, password, email) VALUES (?,?,?,?);";

        try (var connection = DriverManager.getConnection(JDBC);
             var prepareStatement = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
            prepareStatement.setString(1, client.getName());
            prepareStatement.setString(2, client.getLogin());
            prepareStatement.setString(3, client.getPassword());
            prepareStatement.setString(4, client.getEmail());

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
    public Optional<Client> findById(long idClient) {
        var selectSql = "SELECT * FROM products_sobolev_ma.clients where id = ?";

        try (var connection = DriverManager.getConnection(JDBC);
             var prepareStatement = connection.prepareStatement(selectSql)) {
            prepareStatement.setLong(1, idClient);

            var resultSet = prepareStatement.executeQuery();

            if (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String login = resultSet.getString("username");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String promocode = resultSet.getString("promocode");
                Client client = new Client(id,name,login,password,email,new Cart(new ArrayList<Product>(), promocode));

                return Optional.of(client);
            }

            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteById(long idClient) {

        var selectSql = "DELETE FROM products_sobolev_ma.clients where id = ?";

        try (var connection = DriverManager.getConnection(JDBC);
             var prepareStatement = connection.prepareStatement(selectSql)) {
            prepareStatement.setLong(1, idClient);

            var rows = prepareStatement.executeUpdate();

            return rows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
