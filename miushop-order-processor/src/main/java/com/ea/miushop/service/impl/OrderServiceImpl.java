package com.ea.miushop.service.impl;

import com.ea.miushop.domain.*;
import com.ea.miushop.repository.ItemRepository;
import com.ea.miushop.repository.OrderRepository;
import com.ea.miushop.repository.PurchaseOrderRepository;
import com.ea.miushop.service.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductService productService;

    @Autowired
    ItemService itemService;

    @Autowired
    InventoryService inventoryService;

    @Autowired
    PurchaseOrderService purchaseOrderService;

    @Autowired
    private ApplicationContext context;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrder(Long id) {
        return orderRepository.getOne(id);
    }

    @Override
    public void makeOrder(List<Order> orderList) {
        for(Order order: orderList){
            Order newOrder = new Order();
            System.out.println("******** Order Received ********");
            for(Item i: order.getItems()){
                Item item = new Item();
                item.setQuantity(i.getQuantity());
                item.setProduct(i.getProduct());
                System.out.print("******** Product: " + i.getProduct().getProductName()+ " ********");
                System.out.println(" Quantity: " + i.getQuantity()+ " ********");
                item.setOrder(newOrder);
                itemService.saveItem(item);
                orderRepository.save(newOrder);
            }
        }

    }

    @Override
    public void processOrder(Long orderId) {

        Order order = orderRepository.getOne(orderId);

        boolean allItemsBought = true;

        List<Item> itemList = itemService.findAllByOrder(order);

        for(Item i: itemList){

            if (!i.isBought()) {
                Long productId = i.getProduct().getProductId();
                int orderQuantity = i.getQuantity();
                int quantityInInventory = inventoryService.getInventoryQuantity(productId);

                if (quantityInInventory >= orderQuantity) {
                    Inventory inventory = inventoryService.getInventoryByProduct(productId);
                    inventory.setQuantity(quantityInInventory-orderQuantity);
                    inventoryService.updateInventory(inventory);
                    i.setBought(true);

                } else {
                    allItemsBought = false;
                    PurchaseOrder purchaseOrder = new PurchaseOrder();
                    purchaseOrder.setOrder(order);
                    int neededQuantity = orderQuantity - quantityInInventory;
                    purchaseOrder.setProduct(i.getProduct());
                    purchaseOrder.setCategory(i.getProduct().getCategory());
                    purchaseOrder.setQuantity(neededQuantity);

                    purchaseOrderService.savePurchaseOrder(purchaseOrder);
                    order.addPurchaseOrder(purchaseOrder);


                    orderRepository.save(order);
                }
            }
        }

        order.setOrderStatus(Order.Status.PROCESSED);
        orderRepository.save(order);

        if(allItemsBought){
            order.setOrderStatus(Order.Status.BOUGHT);
            orderRepository.save(order);
        }
    }

    @Override
    public void sendPurchaseOrders(){
        List<PurchaseOrder> purchaseOrders = purchaseOrderService.getAllPurchaseOrders();

        RabbitTemplate makeOrderTemplate =  context.getBean("purchaseOrderTemplate",RabbitTemplate.class);
        makeOrderTemplate.convertAndSend("purchase.order",purchaseOrders);
        System.out.println("Order sent to purchase order queue");


    }

    @Override
    public void buyOrder(Long purchaseOrderId) {

        PurchaseOrder purchaseOrder = purchaseOrderService.getPurchaseOrder(purchaseOrderId);

        if(purchaseOrder!= null && !purchaseOrder.isBought()){
            int oldQuantity = inventoryService.getInventoryQuantity(purchaseOrder.getProduct());
            int boughtQuantity = purchaseOrder.getQuantity();

            Inventory inventory = inventoryService.getInventoryByProduct(purchaseOrder.getProduct());
            inventory.setQuantity(oldQuantity+boughtQuantity);

            inventoryService.updateInventory(inventory);

            purchaseOrder.setBought(true);

            Order order = orderRepository.findByPurchaseOrders(purchaseOrder);
            processOrder(order.getOrderId());
        }
    }

    @Override
    public void deliverOrder(List<Long> orderId) {
        for(Long oId: orderId){
            Order boughtOrder = orderRepository.getOne(oId);
            boughtOrder.setOrderStatus(Order.Status.DELIVERED);
            orderRepository.save(boughtOrder);

            System.out.println("Order with order ID: " + oId + " Has been Delivered");
        }

    }
}
