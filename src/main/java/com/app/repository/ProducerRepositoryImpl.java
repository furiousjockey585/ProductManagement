package com.app.repository;

import com.app.connection.DbConnection;
import com.app.exceptions.MyException;
import com.app.model.Producer;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProducerRepositoryImpl implements ProducerRepository {

    Connection connection = DbConnection.getInstance().getConnection();

    @Override
    public void addProducer(Producer producer) {
        try {
            final String sql = "insert into Producer (name, budget, countryId) values (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, producer.getName());
            preparedStatement.setBigDecimal(2, producer.getBudget());
            preparedStatement.setInt(3, producer.getCountryId());
            preparedStatement.execute();
        } catch (Exception e) {
            throw new MyException("Error - addProducer", LocalDate.now());
        }
    }

    @Override
    public void updateProducer(Producer producer) {
        try {
            final String sql = "update Producer set name=?, budget=?, countryId=? where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, producer.getName());
            preparedStatement.setBigDecimal(2, producer.getBudget());
            preparedStatement.setInt(3, producer.getCountryId());
            preparedStatement.setInt(4, producer.getId());
            preparedStatement.execute();
        } catch (Exception e) {
            throw new MyException("Error - updateProducer", LocalDate.now());
        }
    }

    @Override
    public void deleteProducer(Integer id) {
        try {
            final String sql = "delete from Producer where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (Exception e) {
            throw new MyException("Error - deleteProducer", LocalDate.now());
        }
    }

    @Override
    public Optional<Producer> findOneById(Integer id) {
        Optional<Producer> optionalProducer = Optional.empty();

        try {
            final String sql = "select id, name, budget, countryId from Producer where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                optionalProducer = Optional.of(new Producer(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getBigDecimal(3),
                        resultSet.getInt(4)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException("Error - findOneById", LocalDate.now());
        }
        return optionalProducer;
    }

    @Override
    public Optional<Producer> findOneByName(String name) {
        Optional<Producer> optionalProducer = Optional.empty();

        try {
            final String sql = "select id, name, budget, countryId from Producer where name=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                optionalProducer = Optional.of(new Producer(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getBigDecimal(3),
                        resultSet.getInt(4)
                ));
            }
        } catch (Exception e) {
            throw new MyException("Error - findOneByName", LocalDate.now());
        }
        return optionalProducer;
    }

    @Override
    public List<Producer> findAll() {
        List<Producer> producerList = new ArrayList<>();

        try {
            final String sql = "select id, name, budget, countryId from Producer";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                producerList.add(new Producer(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getBigDecimal(3),
                        resultSet.getInt(4)
                ));
            }
        } catch (Exception e) {
            throw new MyException("Error - findAlll", LocalDate.now());
        }
        return producerList;
    }
}
