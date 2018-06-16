package com.app.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Producer {
    private Integer id;
    private String name;
    private BigDecimal budget;
    private Integer countryId;

    public Producer(String string, int anInt) {
    }

    public Producer(Integer id, String name, BigDecimal budget, Integer countryId) {
        this.id = id;
        this.name = name;
        this.budget = budget;
        this.countryId = countryId;
    }

    public Producer(String name, BigDecimal budget, Integer countryId) {
        this.name = name;
        this.budget = budget;
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

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    @Override
    public String toString() {
        return "Producer{id = " + id +
                ", name = " + name +
                ", budget = " + budget +
                ", countryId = " + countryId + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producer producer = (Producer) o;
        return Objects.equals(id, producer.id) &&
                Objects.equals(name, producer.name) &&
                Objects.equals(budget, producer.budget) &&
                Objects.equals(countryId, producer.countryId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, budget, countryId);
    }
}
