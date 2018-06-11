package com.app.connection;

import com.app.exceptions.MyException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.LocalDate;

public class DbConnection {
    private static DbConnection ourInstance = new DbConnection();
    public static DbConnection getInstance() {
        return ourInstance;
    }

    private final static String DRIVER = "org.sqlite.JDBC";
    private final static String DB_NAME = "jdbc:sqlite:Test.db";

    private Connection connection;

    private DbConnection() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(DB_NAME);
            createTables();
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException("Error - connecting to database", LocalDate.now());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    private void createTables() {
        try {
            final String sqlCategory =
                    "create table if not exists Category ( " +
                            "id integer primary key autoincrement, " +
                            "name varchar(50) unique not null " +
                            ")";
            final String sqlCountry =
                    "create table if not exists Country ( " +
                            "id integer primary key autoincrement, " +
                            "name varchar(50) unique not null " +
                            ")";
            final String sqlProducer =
                    "create table if not exists Producer ( " +
                            "id integer primary key autoincrement, " +
                            "name varchar(50) not null, " +
                            "budget decimal(5,2) default 0, " +
                            "countryId integer, " +
                            "foreign key (countryId) references country(id) on delete cascade on update cascade " +
                            ")";
            final String sqlProduct =
                    "create table if not exists Product ( " +
                            "id integer primary key autoincrement, " +
                            "name varchar(50) not null, " +
                            "price decimal(5,2) default 0, " +
                            "categoryId integer, " +
                            "producerId integer, " +
                            "foreign key (categoryId) references category(id) on delete cascade on update cascade, " +
                            "foreign key(producerId) references producer(id) on delete cascade on update cascade " +
                            ")";
            final String sqlCustomer =
                    "create table if not exists Customer ( " +
                            "id integer primary key autoincrement,\n" +
                            "name varchar(50) not null,\n" +
                            "surname varchar(50) not null,\n" +
                            "age integer not null,\n" +
                            "countryId integer,\n" +
                            "foreign key (countryId) references country(id) on delete cascade on update cascade\n" +
                            ")";
            final String sqlOrderTable =
                    "create table if not exists OrderTable ( " +
                            "id integer primary key autoincrement, " +
                            "productId integer, " +
                            "customerId integer, " +
                            "quantity integer not null, " +
                            "discount integer, " +
                            "date datetime not null, " +
                            "foreign key (productId) references product(id) on delete cascade on update cascade, " +
                            "foreign key (customerId) references customer(id) on delete cascade on update cascade " +
                            ")";
            Statement statement = connection.createStatement();
            statement.execute(sqlCategory);
            statement.execute(sqlCountry);
            statement.execute(sqlProducer);
            statement.execute(sqlProduct);
            statement.execute(sqlCustomer);
            statement.execute(sqlOrderTable);
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException("Error - creating tables", LocalDate.now());
        }
    }
}
