package com.app;

import com.app.model.*;
import com.app.repository.*;
import com.app.service.Service;
import com.app.service.ServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        ProductRepository productRepository = new ProductRepositoryImpl();
        ProducerRepository producerRepository = new ProducerRepositoryImpl();
        OrderTableRepository orderTableRepository = new OrderTableRepositoryImpl();
        CustomerRepository customerRepository = new CustomerRepositoryImpl();
        CountryRepository countryRepository = new CountryRepositoryImpl();
        CategoryRepository categoryRepository = new CategoryRepositoryImpl();
        Service service = new ServiceImpl();

        Scanner scanner = new Scanner(System.in);
        int opcja;

        System.out.println("Podaj numer opcji");
        System.out.println("1. Producent, ktory sprzedał najwiecej produktow");
        System.out.println("2. Zestawienie - producent i srednia cena sprzedanych przez niego produtkow po uwzglednieniu zniżek");
        System.out.println("3. Kraj, z którego pochodzi klient, ktory dokonał łącznie zakupow o najwiekszej kwocie");
        System.out.println("4. Zestawienie - ilość produktow kupowanych w poszczegolnych miesiacach");
        System.out.println("5. Zestawienie - straty poszczegolnych producentów na sprzedanych produktach w związku z zastosowanymi zniżkami");
        System.out.println("6. Imiona i nazwiska osob, ktore dokonaly zakupow na kwote co najmniej x ");
        opcja = scanner.nextInt();

        switch (opcja) {
            case 1:
                System.out.println(service.producerOfMaxQuantityOfProducts());
                break;
            case 2:
                System.out.println(service.producerListAvgPricesWithDiscounts());
                break;
            case 3:
                System.out.println(service.countryOfCustomerWithMaxOrderPrice());
                break;
            case 4:
                System.out.println(service.quantityOfProductsPerMonth());
                break;
            case 5:
                System.out.println(service.producerListDiscountLosses());
                break;
            case 6:
                System.out.println("Podaj kwotę: ");
                double kw = scanner.nextDouble();
                System.out.println(service.customerOrderX(kw));
                break;
            default:
                System.out.println("Błędna opcja");
        }

    }
}
