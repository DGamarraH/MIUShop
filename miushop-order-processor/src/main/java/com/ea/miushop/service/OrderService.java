package com.ea.miushop.service;

import com.ea.miushop.domain.Order;
import com.ea.miushop.domain.PurchaseOrder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    List<Order> getAllOrders();
    Order getOrder(Long id);
    void makeOrder(List<Order> order);
    void processOrder(Long orderId);
    void buyOrder(Long purchaseOrderId);
    public void sendPurchaseOrders();
    public void deliverOrder(List<Long> orderId);
//    void publishPurchaseOrder();
}
