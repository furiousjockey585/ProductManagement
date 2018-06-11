package com.app.service;

import com.app.model.Country;
import com.app.model.Producer;
import com.app.model.Product;

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


}
