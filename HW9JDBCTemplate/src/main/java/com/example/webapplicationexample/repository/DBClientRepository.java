package com.example.webapplicationexample.repository;

import com.example.webapplicationexample.model.Cart;
import com.example.webapplicationexample.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
import java.util.Optional;

@Repository
public class DBClientRepository implements ClientRepository{



    private final JdbcTemplate jdbcTemplate;

    private static DBCartRepository dbCartRepository;
    @Autowired
    public DBClientRepository(JdbcTemplate jdbcTemplate, DBCartRepository dbCartRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.dbCartRepository = dbCartRepository;
    }

    private static RowMapper<Client> getClientRowMapper() {
        return (resultSet, rowNum) -> {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String email = resultSet.getString("email");
            return new Client(id, name, username, password, email, new Cart(dbCartRepository.findById(id)));
        };
    }

    @Override
    public long save(Client client) {
        var insertSql = "INSERT INTO clients (name, username, password, email) VALUES (?,?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        PreparedStatementCreator preparedStatementCreator = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, client.getName());
            preparedStatement.setString(2, client.getLogin());
            preparedStatement.setString(3, client.getPassword());
            preparedStatement.setString(4, client.getEmail());

            return preparedStatement;
        };

        jdbcTemplate.update(preparedStatementCreator, keyHolder);

        return (long) (int) keyHolder.getKeys().get("id");
    }

    @Override
    public Optional<Client> findById(long idClient) {
        var selectSql = "SELECT * FROM clients where id = ?";

        PreparedStatementCreator preparedStatementCreator = connection -> {
            var prepareStatement = connection.prepareStatement(selectSql);
            prepareStatement.setLong(1, idClient);

            return prepareStatement;
        };

        RowMapper<Client> clientRowMapper = getClientRowMapper();

        List<Client> clients = jdbcTemplate.query(preparedStatementCreator, clientRowMapper);

        return clients.stream().findFirst();
    }


    @Override
    public boolean deleteById(long idClient) {

        var deleteSql = "DELETE FROM clients where id = ?";
        PreparedStatementCreator preparedStatementCreator = connection -> {
            var prepareStatement = connection.prepareStatement(deleteSql);
            prepareStatement.setLong(1, idClient);

            return prepareStatement;
        };
        int rows = jdbcTemplate.update(preparedStatementCreator);

        return rows > 0;
    }

}
