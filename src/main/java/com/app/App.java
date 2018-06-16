package com.app;

import com.app.model.*;
import com.app.repository.*;
import com.app.service.Service;
import com.app.service.ServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Service service = new ServiceImpl();

        Scanner scanner = new Scanner(System.in);
        int opcja;

        do {
            System.out.println("PRODUCTS MANAGEMENT");
            System.out.println("Choose number of option: ");
            System.out.println("1. Producer who sold the most of products");
            System.out.println("2. List of producers with average prices of sold products (discounts included)");
            System.out.println("3. Country of a client who had the purchase with a highest overall price");
            System.out.println("4. List of months with a quantity of products which was sold");
            System.out.println("5. List of producer losses due to discounts");
            System.out.println("6. List of names of a people who purchased products with a price at least (your value)");
            System.out.println("7. Managing database (adding, deleting, updating, viewing)");
            System.out.println("8. Quit");
            opcja = scanner.nextInt();
            System.out.println();
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
                    System.out.println("Give a price: ");
                    double kw = scanner.nextDouble();
                    System.out.println(service.customerOrderX(kw));
                    break;
                case 7:
                    System.out.println("Choose your option: ");
                    System.out.println("1.Category");
                    System.out.println("2.Country");
                    System.out.println("3.Customer");
                    System.out.println("4.Order Table");
                    System.out.println("5.Producer");
                    System.out.println("6.Product");
                    opcja = scanner.nextInt();
                    switch (opcja) {
                        case 1:
                            System.out.println("Choose option: ");
                            System.out.println("1. Add Category");
                            System.out.println("2. Update Category");
                            System.out.println("3. Delete Category");
                            System.out.println("4. View Category");
                            opcja = scanner.nextInt();
                            switch (opcja) {
                                case 1:
                                    System.out.println("Insert Category name: ");
                                    scanner.nextLine();
                                    service.addCategory(new Category(scanner.nextLine()));
                                    break;
                                case 2:
                                    System.out.println("Insert Id and new name of Category: ");
                                    int id = scanner.nextInt();
                                    scanner.nextLine();
                                    service.updateCategory(new Category(id, scanner.nextLine()));
                                    break;
                                case 3:
                                    System.out.println("Insert Id of Category to delete: ");
                                    service.deleteCategory(scanner.nextInt());
                                    break;
                                case 4:
                                    System.out.println(service.categoryFindAll());
                                    break;
                            }; break;
                        case 2:
                            System.out.println("Choose option: ");
                            System.out.println("1. Add Country");
                            System.out.println("2. Update Country");
                            System.out.println("3. Delete Country");
                            System.out.println("4. View Countries");
                            opcja = scanner.nextInt();
                            switch (opcja) {
                                case 1:
                                    System.out.println("Insert Country name: ");
                                    scanner.nextLine();
                                    service.addCountry(new Country((scanner.nextLine())));
                                    break;
                                case 2:
                                    System.out.println("Insert Id and new name of Country: ");
                                    int id = scanner.nextInt();
                                    scanner.nextLine();
                                    service.updateCountry(new Country(id, scanner.nextLine()));
                                    break;
                                case 3:
                                    System.out.println("Insert Id of Country to delete: ");
                                    service.deleteCountry(scanner.nextInt());
                                    break;
                                case 4:
                                    System.out.println(service.countryFindAll());
                                    break;
                            }; break;
                        case 3:
                            System.out.println("Choose option: ");
                            System.out.println("1. Add Customer");
                            System.out.println("2. Update Customer");
                            System.out.println("3. Delete Customer");
                            System.out.println("4. View Customers");
                            opcja = scanner.nextInt();
                            switch (opcja) {
                                case 1:
                                    System.out.println("Insert Customer name, surname, age, countryId: ");
                                    scanner.nextLine();
                                    service.addCustomer(new Customer(
                                            scanner.nextLine(),
                                            scanner.nextLine(),
                                            scanner.nextInt(),
                                            scanner.nextInt()));
                                    break;
                                case 2:
                                    System.out.println("Insert Id and new name of Customer: ");
                                    int id = scanner.nextInt();
                                    scanner.nextLine();
                                    service.updateCustomer(new Customer(
                                            id,
                                            scanner.nextLine(),
                                            scanner.nextLine(),
                                            scanner.nextInt(),
                                            scanner.nextInt()));
                                    break;
                                case 3:
                                    System.out.println("Insert Id of Customer to delete: ");
                                    service.deleteCustomer(scanner.nextInt());
                                    break;
                                case 4:
                                    System.out.println(service.customerFindAll());
                                    break;
                            }; break;
                        case 4:
                            System.out.println("Choose option: ");
                            System.out.println("1. Add Order");
                            System.out.println("2. Update Order");
                            System.out.println("3. Delete Order");
                            System.out.println("4. View Orders");
                            opcja = scanner.nextInt();
                            switch (opcja) {
                                case 1:
                                    System.out.println("Insert order: productId, customerId, quantity, discount: ");
                                    scanner.nextLine();
                                    service.addOrder(new OrderTable(
                                            scanner.nextInt(),
                                            scanner.nextInt(),
                                            scanner.nextInt(),
                                            scanner.nextInt(),
                                            LocalDate.now()
                                    ));
                                    break;
                                case 2:
                                    System.out.println("Insert Id and new order: productId, customerId, quantity, discount: ");
                                    int id = scanner.nextInt();
                                    scanner.nextLine();
                                    service.updateOrde(new OrderTable(
                                            id,
                                            scanner.nextInt(),
                                            scanner.nextInt(),
                                            scanner.nextInt(),
                                            scanner.nextInt(),
                                            LocalDate.now()
                                    ));
                                case 3:
                                    System.out.println("Insert Id of order to delete: ");
                                    service.deleteOrder(scanner.nextInt());
                                    break;
                                case 4:
                                    System.out.println(service.orderTableFindAll());
                                    break;
                            }; break;
                        case 5:
                            System.out.println("Choose option: ");
                            System.out.println("1. Add Producer");
                            System.out.println("2. Update Producer");
                            System.out.println("3. Delete Producer");
                            System.out.println("4. View Producers");
                            opcja = scanner.nextInt();
                            switch (opcja) {
                                case 1:
                                    System.out.println("Insert Producer: name, budget, countryId: ");
                                    scanner.nextLine();
                                    service.addProducer(new Producer(
                                            scanner.nextLine(),
                                            BigDecimal.valueOf(scanner.nextDouble()),
                                            scanner.nextInt()
                                    ));
                                    break;
                                case 2:
                                    System.out.println("Insert Id and new Producer: name, budget, countryId: ");
                                    int id = scanner.nextInt();
                                    scanner.nextLine();
                                    service.updateProducer(new Producer(
                                            id,
                                            scanner.nextLine(),
                                            BigDecimal.valueOf(scanner.nextDouble()),
                                            scanner.nextInt()
                                    ));
                                case 3:
                                    System.out.println("Insert Id of Producer to delete: ");
                                    service.deleteProducer(scanner.nextInt());
                                    break;
                                case 4:
                                    System.out.println(service.producerFindAll());
                                    break;
                            }; break;
                        case 6:
                            System.out.println("Choose option: ");
                            System.out.println("1. Add Product");
                            System.out.println("2. Update Product");
                            System.out.println("3. Delete Product");
                            System.out.println("4. View Products");
                            opcja = scanner.nextInt();
                            switch (opcja) {
                                case 1:
                                    System.out.println("Insert Product: name, price, categoryId, producerId: ");
                                    scanner.nextLine();
                                    service.addProduct(new Product(
                                            scanner.nextLine(),
                                            BigDecimal.valueOf(scanner.nextDouble()),
                                            scanner.nextInt(),
                                            scanner.nextInt()
                                    ));
                                    break;
                                case 2:
                                    System.out.println("Insert Id and new Product: name, price, categoryId, producerId: ");
                                    int id = scanner.nextInt();
                                    scanner.nextLine();
                                    service.updateProduct(new Product(
                                            id,
                                            scanner.nextLine(),
                                            BigDecimal.valueOf(scanner.nextDouble()),
                                            scanner.nextInt(),
                                            scanner.nextInt()
                                    ));
                                    break;
                                case 3:
                                    System.out.println("Insert Id of Product to delete: ");
                                    service.deleteProduct(scanner.nextInt());
                                    break;
                                case 4:
                                    System.out.println(service.productFindAll());
                                    break;
                            }; break;
                        default:
                            System.out.println("Wrong number");
                    };
                    break;
                case 8: break;
                default:
                    System.out.println("Wrong number");
            }
            if (opcja !=8) {
                System.out.println();
                System.out.println("Zakończyć? (T/N) ");
                scanner.nextLine();
                String s = scanner.nextLine();
                if (s.matches("[T|t]")) {
                    opcja = 8;
                }
            }
        }while (opcja != 8);

    }
}
