package com.app.repository;

import com.app.connection.DbConnection;
import com.app.exceptions.MyException;
import com.app.model.Category;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryRepositoryImpl implements CategoryRepository {

    private Connection connection = DbConnection.getInstance().getConnection();

    @Override
    public void addCategory(Category category) {
        try {
            final String sql = "insert into Category (name) values (?) ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, category.getName());
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException("Error - addCategory", LocalDate.now());
        }
    }

    @Override
    public void updateCategory(Category category) {
        try {
            final String sql = "update Category set name=? where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, category.getName());
            preparedStatement.setInt(2, category.getId());
            preparedStatement.execute();
        } catch (Exception e) {
            throw new MyException("Error - updateCategory", LocalDate.now());
        }
    }

    @Override
    public void deleteCategory(Integer id) {

        try {
            final String sql = "delete from Category where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (Exception e) {
            throw new MyException("Error - deleteCategory", LocalDate.now());
        }
    }

    @Override
    public Optional<Category> findOneById(Integer id) {
        Optional<Category> optionalCategory = Optional.empty();
        try {
            final String sql = "select id, name from Category where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                optionalCategory = Optional.of(new Category(
                        resultSet.getInt(1),
                        resultSet.getString(2)
                ));
            }
        } catch (Exception e) {
            throw new MyException("Error - findOneById", LocalDate.now());
        }
        return optionalCategory;
    }

    @Override
    public Optional<Category> findOneByName(String name) {
        Optional<Category> optionalCategory = Optional.empty();
        try {
            final String sql = "select id, name from Category where name=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                optionalCategory = Optional.of(new Category(
                        resultSet.getInt(1),
                        resultSet.getString(2)
                ));
            }
        }catch (Exception e) {
            throw new MyException("Error - findOneByName", LocalDate.now());
        }
        return optionalCategory;
    };

    @Override
    public List<Category> findAll() {
        List<Category> elements = new ArrayList<>();
        try {
            final String sql = "select id, name from Category";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                elements.add(new Category(
                        resultSet.getInt(1),
                        resultSet.getString(2)
                ));
            };
        } catch (Exception e) {
            throw new MyException("Error - findAll", LocalDate.now());
        }
        return elements;
    }
}
