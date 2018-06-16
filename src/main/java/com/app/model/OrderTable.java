package com.app.model;

import java.time.LocalDate;
import java.util.Objects;

public class OrderTable {
    private Integer id;
    private Integer productId;
    private Integer customerId;
    private Integer quantity;
    private Integer discount;
    private LocalDate date;

    public OrderTable() {
    }

    public OrderTable(Integer productId, Integer customerId, Integer quantity, Integer discount, LocalDate date) {
        this.productId = productId;
        this.customerId = customerId;
        this.quantity = quantity;
        this.discount = discount;
        this.date = date;
    }

    public OrderTable(Integer id, Integer productId, Integer customerId, Integer quantity, Integer discount, LocalDate date) {
        this.id = id;
        this.productId = productId;
        this.customerId = customerId;
        this.quantity = quantity;
        this.discount = discount;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "OrderTable{id = " + id +
                ", productId = " + productId +
                ", customerId = " + customerId +
                ", quantity = " + quantity +
                ", discount = " + discount +
                ", date = " + date + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderTable that = (OrderTable) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(productId, that.productId) &&
                Objects.equals(customerId, that.customerId) &&
                Objects.equals(quantity, that.quantity) &&
                Objects.equals(discount, that.discount) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, productId, customerId, quantity, discount, date);
    }
}
