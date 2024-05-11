package com.project.orderxmlfiltered;


import java.time.LocalDate;
import java.util.List;

public interface IOrderService {
    List<Order> register(List<Order> orders);

    List<Order> getAll(LocalDate date);

    Order getById(Long id);
}
