package com.ea.miushop.service.impl;

import com.ea.miushop.domain.Order;
import com.ea.miushop.domain.Product;
import com.ea.miushop.domain.PurchaseOrder;
import com.ea.miushop.repository.PurchaseOrderRepository;
import com.ea.miushop.service.OrderService;
import com.ea.miushop.service.PurchaseOrderService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {
    @Autowired
    PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    OrderService orderService;

    @Autowired
    ApplicationContext context;

    @Override
    public List<PurchaseOrder> getAllPurchaseOrders() {
        return purchaseOrderRepository.findAll();
    }

    @Override
    public PurchaseOrder getPurchaseOrder(Long id) {
        return purchaseOrderRepository.getOne(id);
    }

    @Override
    public void savePurchaseOrder(PurchaseOrder purchaseOrder) {
        purchaseOrderRepository.save(purchaseOrder);
    }

    @Override
    public PurchaseOrder findByProduct(Product product) {
        return purchaseOrderRepository.findByProduct(product);
    }

    @Override
    public void buyPurchaseOrders(List<PurchaseOrder> purchaseOrders){

        for(PurchaseOrder p: purchaseOrders){
            Order order = new Order();
            order.setOrderId(p.getOrder().getOrderId());
            order.setOrderStatus(Order.Status.BOUGHT);
            orderService.saveOrder(order);

            PurchaseOrder purchaseOrder = new PurchaseOrder();
            purchaseOrder.setBought(true);
            purchaseOrder.setPurchaseOrderId(p.getPurchaseOrderId());
            purchaseOrder.getOrder().setOrderId(p.getOrder().getOrderId());
            purchaseOrderRepository.save(purchaseOrder);
        }

    }

    @Override
    public void sendBoughtOrders(){
        List<PurchaseOrder> purchasedOrders = purchaseOrderRepository.findAll();
//        List<Order> orderList = orderService.getAllOrders();
        List<Long> orderId = new ArrayList<>();
//
        System.out.println("************* Order id of orders purchased*************");

        for (PurchaseOrder purchaseOrder: purchasedOrders){
            orderId.add(purchaseOrder.getOrder().getOrderId());
            System.out.println(purchaseOrder.getOrder().getOrderId());
        }
        RabbitTemplate purchaseOrderTemplate =  context.getBean("purchasedOrderTemplate",RabbitTemplate.class);
        purchaseOrderTemplate.convertAndSend("purchased.order", orderId);

    }
}
