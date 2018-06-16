package com.app.service;

import com.app.model.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface Service {
    Producer producerOfMaxQuantityOfProducts();
    Map<String, Double> producerListAvgPricesWithDiscounts();
    Country countryOfCustomerWithMaxOrderPrice();
    Map<String, Long> quantityOfProductsPerMonth();
    Map<String, Long> producerListDiscountLosses();
    Map<String, Double> customerOrderX(double x);

    void addCategory(Category category);
    void updateCategory(Category category);
    void deleteCategory(Integer id);
    List<Category> categoryFindAll();

    void addCountry(Country country);
    void updateCountry(Country country);
    void deleteCountry(Integer id);
    List<Country> countryFindAll();

    void addCustomer(Customer customer);
    void updateCustomer(Customer customer);
    void deleteCustomer(Integer id);
    List<Customer> customerFindAll();

    void addOrder(OrderTable orderTable);
    void updateOrde(OrderTable orderTable);
    void deleteOrder(Integer id);
    List<OrderTable> orderTableFindAll();

    void addProducer(Producer producer);
    void updateProducer(Producer producer);
    void deleteProducer(Integer id);
    List<Producer> producerFindAll();

    void addProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(Integer id);
    List<Product> productFindAll();

}
