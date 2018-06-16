package com.app.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    private Integer id;
    private String name;
    private BigDecimal price;
    private Integer categoryId;
    private Integer producerId;

    public Product() {
    }

    public Product(Integer id, String name, BigDecimal price, Integer categoryId, Integer producerId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
        this.producerId = producerId;
    }

    public Product(String name, BigDecimal price, Integer categoryId, Integer producerId) {
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
        this.producerId = producerId;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getProducerId() {
        return producerId;
    }

    public void setProducerId(Integer producerId) {
        this.producerId = producerId;
    }

    @Override
    public String toString() {
        return "Product{id = " + id +
                ", name = " + name +
                ", price = " + price +
                ", categoryId = " + categoryId +
                ", producerId = " + producerId + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(name, product.name) &&
                Objects.equals(price, product.price) &&
                Objects.equals(categoryId, product.categoryId) &&
                Objects.equals(producerId, product.producerId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, price, categoryId, producerId);
    }
}
