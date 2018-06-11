package com.app.repository;

import com.app.connection.DbConnection;
import com.app.exceptions.MyException;
import com.app.model.OrderTable;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderTableRepositoryImpl implements OrderTableRepository {

    Connection connection = DbConnection.getInstance().getConnection();

    @Override
    public void addOrder(OrderTable orderTable) {
        try {
            final String sql = "insert into OrderTable (productId, customerId, quantity, discount, date) values (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, orderTable.getProductId());
            preparedStatement.setInt(2, orderTable.getCustomerId());
            preparedStatement.setInt(3, orderTable.getQuantity());
            preparedStatement.setInt(4, orderTable.getDiscount());
            preparedStatement.setDate(5, Date.valueOf(orderTable.getDate()));
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException("Error - addOrder", LocalDate.now());
        }
    }

    @Override
    public void updateOrde(OrderTable orderTable) {
        try {
            final String sql = "update orderTable set productId=?, customerId=?, quantity=?, discount=?, date=? where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, orderTable.getProductId());
            preparedStatement.setInt(2, orderTable.getCustomerId());
            preparedStatement.setInt(3, orderTable.getQuantity());
            preparedStatement.setInt(4, orderTable.getDiscount());
            preparedStatement.setDate(5, Date.valueOf(orderTable.getDate()));
            preparedStatement.setInt(6, orderTable.getId());
            preparedStatement.execute();
        } catch (Exception e) {
            throw new MyException("Error - updateOrder", LocalDate.now());
        }

    }

    @Override
    public void deleteOrder(Integer id) {
        try {
            final String sql = "delete from OrderTable where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (Exception e) {
            throw new MyException("Error - deleteOrder", LocalDate.now());
        }

    }

    @Override
    public Optional<OrderTable> findOneById(Integer id) {
        Optional<OrderTable> orderTable = Optional.empty();
        try {
            final String sql = "select id, productId, customerId, quantity, discount, date from orderTable where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                orderTable = Optional.of(new OrderTable(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getInt(4),
                        resultSet.getInt(5),
                        resultSet.getDate(6).toLocalDate()
                ));
            }
        } catch (Exception e) {
            throw new MyException("Error - findOneById", LocalDate.now());
        }
        return orderTable;
    }


    @Override
    public List<OrderTable> findAll() {
        List<OrderTable> orderTables = new ArrayList<>();

        try {
            final String sql = "select id, productId, customerId, quantity, discount, date from orderTable";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                orderTables.add(new OrderTable(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getInt(4),
                        resultSet.getInt(5),
                        resultSet.getDate(6).toLocalDate()
                ));
            }
        } catch (Exception e) {
            throw new MyException("Error - findAll", LocalDate.now());
        }
        return orderTables;
    }
}
