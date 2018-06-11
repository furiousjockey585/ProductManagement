package com.app.model;

import java.util.Objects;

public class Customer {
    private Integer id;
    private String name;
    private String surname;
    private Integer age;
    private Integer countryId;

    public Customer() {
    }

    public Customer(Integer id, String name, String surname, Integer age, Integer countryId) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.countryId = countryId;
    }

    public Customer(String name, String surname, Integer age, Integer countryId) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.countryId = countryId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", countryId=" + countryId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) &&
                Objects.equals(name, customer.name) &&
                Objects.equals(surname, customer.surname) &&
                Objects.equals(age, customer.age) &&
                Objects.equals(countryId, customer.countryId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, surname, age, countryId);
    }

}
