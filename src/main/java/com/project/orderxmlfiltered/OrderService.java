package com.project.orderxmlfiltered;

import com.project.orderxmlfiltered.exception.AlreadyExistsException;
import com.project.orderxmlfiltered.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final IOrderRepository repository;
    @Override
    public List<Order> register(List<Order> orders) {
        orders.forEach(order -> {
            repository.findByControlNumber(order.getControlNumber())
                    .ifPresent(u -> { throw new AlreadyExistsException("order.controlNumber'", String.valueOf(order.getControlNumber()));});
            order.setTotalValue(calcTotalValue(order));
        });
        return repository.saveAll(orders);
    }

    private double calcTotalValue(Order order) {
        double totalValue = order.getUnitValue() * order.getQuantity();
        if (order.getQuantity() >= 5 && order.getQuantity() < 10) {
            totalValue *= 0.95;
        }
        if (order.getQuantity() >= 10) {
            totalValue *= 0.90;
        }
        return totalValue;
    }

    @Override
    public List<Order> getAll(LocalDate date) {
        return date == null ? repository.findAll() : repository.findByRegisterDate(date);
    }

    @Override
    public Order getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("order.id", String.valueOf(id)));
    }
}
