package com.app.repository;

import com.app.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    void addCustomer(Customer customer);
    void updateCustomer(Customer customer);
    void deleteCustomer(Integer id);
    Optional<Customer> findOneById(Integer id);
    List<Customer> findByNameAndSurname(String name, String surname);
    List<Customer> findAll();
}
