package com.example.webapplicationexample.services;

import com.example.webapplicationexample.model.Cart;
import com.example.webapplicationexample.model.Client;
import com.example.webapplicationexample.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.expression.Arrays;

import java.math.BigDecimal;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DBPaymentService implements PaymentService{

    private final JdbcTemplate jdbcTemplate;

    public DBPaymentService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public boolean cartSum(long idClient) {
        var selectSql = """
                            select p.id, p.price, p.amount, pc.amount amountcart
                            from products_clients pc
                            join products p on p.id=pc.id_product
                            where pc.id_client = ?""";

        var updateSql = """
                            update products
                            set amount = amount - ?
                            where id = ?""";

        var deleteSql = """
                            delete from products_clients
                            where id_client=?""";

        PreparedStatementCreator preparedStatementCreatorForSelect = connection -> {
            var prepareStatement = connection.prepareStatement(selectSql);
            prepareStatement.setLong(1, idClient);

            return prepareStatement;
        };

        RowMapper<List<Object>> checkAmountRowMapper = getCheckAmountRowMapper();
        List<List<Object>> checkAmountList = jdbcTemplate.query(preparedStatementCreatorForSelect, checkAmountRowMapper);

        for (List<Object> innerList : checkAmountList) {
            if ((int)innerList.get(2) < (int)innerList.get(3)){
                return false;
            }
        }
        int rows = 0;
        for (List<Object> innerList : checkAmountList) {
            PreparedStatementCreator preparedStatementCreatorForUpdate = connection -> {
                var prepareStatement = connection.prepareStatement(updateSql);
                prepareStatement.setInt(1, (int)innerList.get(3));
                prepareStatement.setLong(2, idClient);

                return prepareStatement;
            };
            rows += jdbcTemplate.update(preparedStatementCreatorForUpdate);
        }

        PreparedStatementCreator preparedStatementCreatorForDelete = connection -> {
            var prepareStatement = connection.prepareStatement(deleteSql);
            prepareStatement.setLong(1, idClient);

            return prepareStatement;
        };
        jdbcTemplate.update(preparedStatementCreatorForDelete);

        BigDecimal sum = BigDecimal.valueOf(0);

        for (List<Object> innerList : checkAmountList) {
            sum.add(BigDecimal.valueOf((Double)innerList.get(1)*(int)innerList.get(3)));
        }

        return rows > 0;

    }

    private RowMapper<List<Object>> getCheckAmountRowMapper() {
        return (resultSet, rowNum) -> {
            int id = resultSet.getInt("id");
            Double price = resultSet.getDouble("price");
            int amountProduct = resultSet.getInt("amount");
            int amountCart = resultSet.getInt("amountcart");
            return List.of(id, price, amountProduct, amountCart);
        };
    }
}
