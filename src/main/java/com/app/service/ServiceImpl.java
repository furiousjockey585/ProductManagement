package com.app.service;

import com.app.connection.DbConnection;
import com.app.exceptions.MyException;
import com.app.model.*;
import com.app.repository.*;

import javax.naming.NoInitialContextException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ServiceImpl implements Service {

    CategoryRepository categoryRepository = new CategoryRepositoryImpl();
    CountryRepository countryRepository = new CountryRepositoryImpl();
    CustomerRepository customerRepository = new CustomerRepositoryImpl();
    OrderTableRepository orderTableRepository = new OrderTableRepositoryImpl();
    ProducerRepository producerRepository = new ProducerRepositoryImpl();
    ProductRepository productRepository = new ProductRepositoryImpl();

    Connection connection = DbConnection.getInstance().getConnection();


    @Override
    public Producer producerOfMaxQuantityOfProducts() {

        int id = orderTableRepository
                .findAll()
                .stream()

                // ponizej masz pogrupowanie klucz -> producerId a wartosc to lista zamowien tego producera
                .collect(Collectors.groupingBy(o -> productRepository.findOneById(o.getProductId()).orElseThrow(NullPointerException::new).getProducerId()))

                .entrySet()
                .stream()
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().stream().collect(Collectors.summarizingInt(o -> o.getQuantity())).getSum()))
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(e -> e.getValue(), Comparator.reverseOrder()))
                .findFirst()
                .orElseThrow(NullPointerException::new)
                .getKey();
        return producerRepository.findOneById(id).orElseThrow(() -> new NullPointerException("NIE ZNALEZIONO PRODUCENTA OD ID: " + id));

    }

    @Override
    public Map<String, Double> producerListAvgPricesWithDiscounts() {

        return orderTableRepository
                .findAll()
                .stream()
                .collect(Collectors.groupingBy(o -> productRepository.findOneById(o.getProductId()).orElseThrow(NullPointerException::new).getProducerId()))

                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        e -> producerRepository.findOneById(e.getKey()).orElseThrow(NullPointerException::new).getName(),
                        e -> e.getValue().stream().collect(Collectors.summarizingDouble(
                                o -> productRepository.findOneById(o.getProductId()).orElseThrow(NullPointerException::new).getPrice().doubleValue()
                                        - orderTableRepository.findOneById(o.getProductId()).orElseThrow(NullPointerException::new).getDiscount())).getAverage()
                ));
    }

    @Override
    public Country countryOfCustomerWithMaxOrderPrice() {
        Country maxCountry = orderTableRepository
                .findAll()
                .stream()
                .collect(Collectors.groupingBy(o -> customerRepository.findOneById(o.getCustomerId()).orElseThrow(NullPointerException::new)))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        p -> countryRepository.findOneById(p.getKey().getCountryId()),
                        p -> p.getValue().stream().collect(Collectors.summarizingDouble(o -> o.getQuantity() * productRepository.findOneById(o.getProductId()).orElseThrow(NullPointerException::new).getPrice().doubleValue())).getSum()
                ))
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(e -> e.getValue(), Comparator.reverseOrder()))
                .findFirst()
                .orElseThrow(NullPointerException::new)
                .getKey()
                .get();
        return maxCountry;
    }

    @Override
    public Map<String, Long> quantityOfProductsPerMonth() {

        return orderTableRepository
                .findAll()
                .stream()
                .collect(Collectors.groupingBy(p -> orderTableRepository.findOneById(p.getProductId()).orElseThrow(NullPointerException::new).getDate().getMonth()))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(o -> o.getKey().toString(), o -> o.getValue().stream().collect(Collectors.summarizingInt(p -> p.getQuantity())).getSum()));

    }

    @Override
    public Map<String, Long> producerListDiscountLosses() {

        return orderTableRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(o -> productRepository.findOneById(o.getProductId()).orElseThrow(NullPointerException::new).getProducerId()))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        o -> producerRepository.findOneById(o.getKey()).orElseThrow(NullPointerException::new).getName(),
                        o -> o.getValue().stream().collect(Collectors.summarizingInt(p -> p.getQuantity() * p.getDiscount())).getSum()));
    }

    @Override
    public Map<String, Double> customerOrderX(double x) {
        return orderTableRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(o -> customerRepository.findOneById(o.getCustomerId()).orElseThrow(NullPointerException::new).getId()))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        o -> o.getKey(),
                        o -> o.getValue()
                                .stream().collect(Collectors.summarizingDouble(p -> p.getQuantity() * productRepository.findOneById(p.getProductId()).orElseThrow(NullPointerException::new).getPrice().doubleValue())).getSum()
                ))
                .entrySet()
                .stream()
                .filter(p -> p.getValue() >= x)
                .collect(Collectors.toMap(
                        p -> customerRepository.findOneById(p.getKey()).orElseThrow(NullPointerException::new).getName(),
                        p -> p.getValue()
                ));
    }

    @Override
    public void addCategory(Category category) {
        categoryRepository.addCategory(category);
    }

    @Override
    public void updateCategory(Category category) {
        categoryRepository.addCategory(category);
    }

    @Override
    public void deleteCategory(Integer id) {
        categoryRepository.deleteCategory(id);
    }

    @Override
    public List<Category> categoryFindAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void addCountry(Country country) {
        countryRepository.addCountry(country);
    }

    @Override
    public void updateCountry(Country country) {
        countryRepository.updateCountry(country);
    }

    @Override
    public void deleteCountry(Integer id) {
        countryRepository.deleteCountry(id);
    }

    @Override
    public List<Country> countryFindAll() {
        return countryRepository.findAll();
    }

    @Override
    public void addCustomer(Customer customer) {
        customerRepository.addCustomer(customer);
    }

    @Override
    public void updateCustomer(Customer customer) {
        customerRepository.updateCustomer(customer);
    }

    @Override
    public void deleteCustomer(Integer id) {
        customerRepository.deleteCustomer(id);
    }

    @Override
    public List<Customer> customerFindAll() {
        return customerRepository.findAll();
    }

    @Override
    public void addOrder(OrderTable orderTable) {
        orderTableRepository.addOrder(orderTable);
    }

    @Override
    public void updateOrde(OrderTable orderTable) {
        orderTableRepository.updateOrde(orderTable);
    }

    @Override
    public void deleteOrder(Integer id) {
        orderTableRepository.deleteOrder(id);
    }

    @Override
    public List<OrderTable> orderTableFindAll() {
        return orderTableRepository.findAll();
    }

    @Override
    public void addProducer(Producer producer) {
        producerRepository.addProducer(producer);
    }

    @Override
    public void updateProducer(Producer producer) {
        producerRepository.updateProducer(producer);
    }

    @Override
    public void deleteProducer(Integer id) {
        producerRepository.deleteProducer(id);
    }

    @Override
    public List<Producer> producerFindAll() {
        return producerRepository.findAll();
    }

    @Override
    public void addProduct(Product product) {
        productRepository.addProduct(product);
    }

    @Override
    public void updateProduct(Product product) {
        productRepository.updateProduct(product);
    }

    @Override
    public void deleteProduct(Integer id) {
        productRepository.deleteProduct(id);
    }

    @Override
    public List<Product> productFindAll() {
        return productRepository.findAll();
    }

}