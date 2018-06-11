package com.app.repository;

import com.app.connection.DbConnection;
import com.app.exceptions.MyException;
import com.app.model.Customer;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerRepositoryImpl implements CustomerRepository{

    Connection connection = DbConnection.getInstance().getConnection();

    @Override
    public void addCustomer(Customer customer) {
        try {
            final String sql = "insert into Customer (name, surname, age, countryId) values (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getSurname());
            preparedStatement.setInt(3, customer.getAge());
            preparedStatement.setInt(4, customer.getCountryId());
            preparedStatement.execute();
        } catch (Exception e) {
            throw new MyException("Error - addCustomer", LocalDate.now());
        }
    }

    @Override
    public void updateCustomer(Customer customer) {
        try {
            final String sql = "update Customer set name=?, surname=?, age=?, countryId=? where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getSurname());
            preparedStatement.setInt(3, customer.getAge());
            preparedStatement.setInt(4, customer.getCountryId());
            preparedStatement.setInt(5, customer.getId());
            preparedStatement.execute();
        } catch (Exception e) {
            throw new MyException("Error - updateCustomer", LocalDate.now());
        }
    }

    @Override
    public void deleteCustomer(Integer id) {
        try {
            final String sql = "delete from Customer where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new MyException("Error - deleteCustomer", LocalDate.now());
        }
    }

    @Override
    public Optional<Customer> findOneById(Integer id) {
        Optional<Customer> optionalCustomer = Optional.empty();
        try {
            final String sql = "select id, name, surname, age, countryId from Customer where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                optionalCustomer = Optional.of(new Customer(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getInt(5)
                ));
            }
        } catch (Exception e) {
            throw new MyException("Error - findById", LocalDate.now());
        }
        return optionalCustomer;
    }

    @Override
    public List<Customer> findByNameAndSurname(String name, String surname) {
        List<Customer> customerList = new ArrayList<>();

        try {
            final String sql = "select id, name, surname, age, countryId from Customer where name=? and surname=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                customerList.add(new Customer(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getInt(5)
                ));
            }
        } catch (Exception e) {
            throw new MyException("Error - findOneByNameAndSurname", LocalDate.now());
        }
        return customerList;
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        try {
            final String sql = "select id, name, surname, age, countryId from Customer";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                customers.add(new Customer(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getInt(5)
                ));
            }
        } catch (Exception e) {
            throw new MyException("Error - findAll", LocalDate.now());
        }
        return customers;
    }
}
