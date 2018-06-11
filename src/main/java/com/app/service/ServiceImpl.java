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

//    2. Wypisz nazwe producenta, ktory sprzedal najwiecej produktow

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

//    3. Wykonaj zestawienie, w ktorym umiescisz nazwe producenta i srednia cene sprzedanych przez niego produtkow po uwzglednieniu znizek

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

//    Podaj nazwe kraju, z ktorego pochodzi klient, ktory dokonal lacznie zakupow najwiekszej kwocie

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

//    5. Wykonaj zestawienie w ktorym podasz ile produktow kupowano w poszczegolnych miesiacach

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

//    6. Wykonaj zestawienie w ktorym podasz ile stracili poszczegolni producenci na sprzedanych priduktach w zwiazku z zastosowanymi znizkami


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

//    7. Podaj imiona i nazwieska osob, ktore dokonaly zakupow na kwote co najmniej x (x podajesz samodzielnie). Posortuj
// zestawienie malejaco wedlug wieku, w przypadku takich samych wieków posortuj alfabetycznie najpierw po imieniu potem po nazwisku.

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
//                  TO DO sortowanie
//                .sorted(Comparator.comparing(p -> customerRepository.findOneById(p.getKey()).orElseThrow(NullPointerException::new).getAge()))
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
                .collect(Collectors.toMap(
                        p -> customerRepository.findOneById(p.getKey()).orElseThrow(NullPointerException::new).getName(),
                        p -> p.getValue()
                ));
    }

}