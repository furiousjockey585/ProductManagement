package com.app.repository;

import com.app.connection.DbConnection;
import com.app.exceptions.MyException;
import com.app.model.Country;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CountryRepositoryImpl implements CountryRepository {
    Connection connection = DbConnection.getInstance().getConnection();

    @Override
    public void addCountry(Country country) {
        try {
            final String sql = "insert into Country ( name) values (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, country.getName());
            preparedStatement.execute();
        } catch (Exception e) {
            throw new MyException("Error - addCountry", LocalDate.now());
        }
    }

    @Override
    public void updateCountry(Country country) {
        try {
            final String sql = "update Country set name=? where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, country.getName());
            preparedStatement.setInt(2, country.getId());
            preparedStatement.execute();
        } catch (Exception e) {
            throw new MyException("Error - updateCountry", LocalDate.now());
        }
    }

    @Override
    public void deleteCountry(Integer id) {
        try {
            final String sql = "delete from Country where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (Exception e) {
            throw new MyException("Error - deleteCountry", LocalDate.now());
        }
    }

    @Override
    public Optional<Country> findOneById(Integer id) {
        Optional<Country> optionalCountry = Optional.empty();
        try {
            final String sql = "select id, name from Country where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                optionalCountry = Optional.of(new Country(
                        resultSet.getInt(1),
                        resultSet.getString(2)
                ));
            }
        } catch (Exception e) {
            throw new MyException("Error - findById", LocalDate.now());
        }
        return optionalCountry;
    }

    @Override
    public Optional<Country> findOneByName(String name) {
        Optional<Country> optionalCountry = Optional.empty();

        try {
            final String sql = "select id, name from Country where name=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                optionalCountry = Optional.of(new Country(
                        resultSet.getInt(1),
                        resultSet.getString(2)
                ));
            }
        } catch (Exception e) {
            throw new MyException("Error - findOneByName", LocalDate.now());
        }
        return optionalCountry;
    }

    @Override
    public List<Country> findAll() {
        List<Country> elements = new ArrayList<>();

        try {
            final String sql = "select id, name from Country";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                elements.add(new Country(
                        resultSet.getInt(1),
                        resultSet.getString(2)
                ));
            }
        } catch (Exception e) {
            throw new MyException("Error - findAll", LocalDate.now());
        }
        return elements;
    }
}
