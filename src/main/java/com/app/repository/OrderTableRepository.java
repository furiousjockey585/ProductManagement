package com.app.repository;

import com.app.model.OrderTable;

import java.util.List;
import java.util.Optional;

public interface OrderTableRepository {
    void addOrder(OrderTable orderTable);
    void updateOrde(OrderTable orderTable);
    void deleteOrder(Integer id);
    Optional<OrderTable> findOneById(Integer id);
    List<OrderTable> findAll();
}
