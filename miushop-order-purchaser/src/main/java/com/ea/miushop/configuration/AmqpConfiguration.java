package com.ea.miushop.configuration;


import com.ea.miushop.service.OrderService;
import com.ea.miushop.service.PurchaseOrderService;
import com.ea.miushop.service.impl.OrderServiceImpl;
import com.ea.miushop.service.impl.PurchaseOrderServiceImpl;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;

import java.util.Arrays;
import java.util.List;

@Configuration
//@ComponentScan("com.ea.miushop")
//@IntegrationComponentScan("com.ea.miushop.integration")
//@EnableIntegration
public class AmqpConfiguration {

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        return connectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }


    //*********Outbound point*********

    //Binding queues to exchanges


    @Bean
    TopicExchange orderExchange() {
        return new TopicExchange("miuShopOrderDirectExchange");
    }



    //PurchaseOrder producer



    //*********Listening point*********

    //Purchase order listener


    @Bean
    PurchaseOrderService purchaseOrderService() {
        return new PurchaseOrderServiceImpl();
    }
    @Bean
    public SimpleMessageListenerContainer orderListenerContainerOnline() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.setQueueNames("purchaseOrder");
        container.setMessageListener(new MessageListenerAdapter(purchaseOrderService(),"buyPurchaseOrders"));
        return container;
    }


    //Purchased order sender
    @Bean
    Queue sendPurchasedOrderQueue() {
        return new Queue("purchasedOrder", true,false,false);
    }

    @Bean
    Binding binding() {

        return (BindingBuilder.bind(sendPurchasedOrderQueue()).to(orderExchange()).with("purchased.order.#")	);
    }


    @Bean
    public RabbitTemplate purchasedOrderTemplate() {
        RabbitTemplate purchaseOrderTemplate= new RabbitTemplate(connectionFactory());
        purchaseOrderTemplate.setRoutingKey("purchased.order");
        purchaseOrderTemplate.setExchange("miuShopOrderDirectExchange");
        purchaseOrderTemplate.setReplyTimeout(2000);
        return purchaseOrderTemplate;
    }
//
//    @Bean
//    List<Binding> bindings() {
//
//        return Arrays.asList(BindingBuilder.bind(purchaseOrderQueue())
//                .to(orderExchange()).with("purchase.order.#"));
//    }

}
