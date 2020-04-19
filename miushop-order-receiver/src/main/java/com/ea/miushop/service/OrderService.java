package com.ea.miushop.service;

import com.ea.miushop.domain.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    void saveOrder(Order order);
    List<Order> getAllOrders();
}
