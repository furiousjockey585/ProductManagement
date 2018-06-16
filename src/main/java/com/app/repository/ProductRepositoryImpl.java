package com.app.repository;

import com.app.connection.DbConnection;
import com.app.exceptions.MyException;
import com.app.model.Product;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepositoryImpl implements ProductRepository {

    Connection connection = DbConnection.getInstance().getConnection();

    @Override
    public void addProduct(Product product) {
        try {
            final String sql = "insert into Product (name, price, categoryId, producerId) values (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setBigDecimal(2, product.getPrice());
            preparedStatement.setInt(3, product.getCategoryId());
            preparedStatement.setInt(4, product.getProducerId());
            preparedStatement.execute();
        } catch (Exception e) {
            throw new MyException("Error - addProduct", LocalDate.now());
        }

    }

    @Override
    public void updateProduct(Product product) {
        try {
            final String sql = "update Product set name=?, price=?, categoryId=?, producerId=? where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setBigDecimal(2, product.getPrice());
            preparedStatement.setInt(3, product.getCategoryId());
            preparedStatement.setInt(4, product.getProducerId());
            preparedStatement.setInt(5, product.getId());
            preparedStatement.execute();
        } catch (Exception e) {
            throw new MyException("Error - updateProduct", LocalDate.now());
        }
    }

    @Override
    public void deleteProduct(Integer id) {
        try {
            final String sql = "delete from Product where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (Exception e) {
            throw new MyException("Error - deleteProduct", LocalDate.now());
        }
    }

    @Override
    public Optional<Product> findOneById(Integer id) {
        Optional<Product> optionalProduct = Optional.empty();

        try {
            final String sql = "select id, name, price, categoryId, producerId from Product where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                optionalProduct = Optional.of(new Product(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getBigDecimal(3),
                        resultSet.getInt(4),
                        resultSet.getInt(5)
                ));
            }
        } catch (Exception e) {
            throw new MyException("Error - findOneById", LocalDate.now());
        }
        return optionalProduct;
    }


    @Override
    public List<Product> findAll() {
        List<Product> productList = new ArrayList<>();

        try {
            final String sql = "select id, name, price, categoryId, producerId from Product";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                productList.add(new Product(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getBigDecimal(3),
                        resultSet.getInt(4),
                        resultSet.getInt(5)
                ));
            }
        } catch (Exception e) {
            throw new MyException("Error - findAll", LocalDate.now());
        }
        return productList;
    }

    @Override
    public Optional<Product> findOneByName(String name) {
        Optional<Product> optionalProduct = Optional.empty();

        try {
            final String sql = "select id, name, price, categoryId, producerId from Product where name=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                optionalProduct = Optional.of(new Product(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getBigDecimal(3),
                        resultSet.getInt(4),
                        resultSet.getInt(5)
                ));
            }
        } catch (Exception e) {
            throw new MyException("Error - findOneById", LocalDate.now());
        }
        return optionalProduct;
    }
}
