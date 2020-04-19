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

    //Binding queues to exchanges


    @Bean
    TopicExchange orderExchange() {
        return new TopicExchange("miuShopOrderDirectExchange");
    }


    @Bean
    public Queue purchaseOrderQueue() {
        return new Queue("purchaseOrder", true,false,false);
    }


    @Bean
    List<Binding> bindings() {

        return Arrays.asList(BindingBuilder.bind(purchaseOrderQueue())
                .to(orderExchange()).with("purchase.order.#"));
    }


    //PurchaseOrder producer

    @Bean
    public RabbitTemplate purchaseOrderTemplate() {
        RabbitTemplate purchaseOrderTemplate= new RabbitTemplate(connectionFactory());
        purchaseOrderTemplate.setRoutingKey("purchase.order");
        purchaseOrderTemplate.setExchange("miuShopOrderDirectExchange");
        purchaseOrderTemplate.setReplyTimeout(2000);
        return purchaseOrderTemplate;
    }

    @Bean
    public SimpleMessageListenerContainer orderListenerContainerOnline() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.setQueueNames("sendOrder");
        container.setMessageListener(new MessageListenerAdapter(orderService(),"makeOrder"));
        return container;
    }

    @Bean
    OrderService orderService() {
        return new OrderServiceImpl();
    }

    @Bean
    Queue sendOrderQueue() {
        return new Queue("sendOrder", true,false,false);
    }

    @Bean
    Binding binding() {

        return (BindingBuilder.bind(sendOrderQueue()).to(orderExchange()).with("make.order.#")	);
    }



    //**********Purchased order listening point**********
    @Bean
    Queue purchasedOrderQueue() {
        return new Queue("purchasedOrder", true,false,false);
    }


    @Bean
    public SimpleMessageListenerContainer purchasedOrderListenerContainerOnline() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.setQueueNames("purchasedOrder");
        container.setMessageListener(new MessageListenerAdapter(orderService(),"deliverOrder"));
        return container;
    }

}
