package com.example.webapplicationexample.services;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.DriverManager;
import java.sql.SQLException;

@Service
public class LocalPaymentService implements PaymentService{

    private static final String JDBC = "jdbc:postgresql://localhost:5432/postgres?user=postgres&password=postgres";

    @Override
    public boolean cartSum(long idClient) {
        var selectSql = """
                            SELECT price, amount
                            FROM products_sobolev_ma.products_carts pc
                            join products_sobolev_ma.products p on p.id=pc.id_product
                            where pc.id_client = ?""";

        try (var connection = DriverManager.getConnection(JDBC);
             var prepareStatement = connection.prepareStatement(selectSql)) {
            prepareStatement.setLong(1, idClient);
            BigDecimal sum = BigDecimal.valueOf(0);
            var resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                int amount = resultSet.getInt("amount");
                double price = resultSet.getDouble("price");
                sum.add(BigDecimal.valueOf(price*amount));
            }

            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
