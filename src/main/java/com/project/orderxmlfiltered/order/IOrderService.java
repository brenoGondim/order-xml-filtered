package com.project.orderxmlfiltered.order;


import java.time.LocalDate;
import java.util.List;

public interface IOrderService {
    List<Order> register(List<Order> orders);

    List<Order> getAll(LocalDate date);

    Order getById(Long id);
}
