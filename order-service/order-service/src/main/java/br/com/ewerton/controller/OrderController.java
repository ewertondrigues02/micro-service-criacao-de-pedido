package br.com.ewerton.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ewerton.dto.OrderCreatedEvent;
import br.com.ewerton.model.Order;
import br.com.ewerton.service.OrderService;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping
    public Order createOrders(@RequestBody Order order) {
        orderService.createOrder(order);
        OrderCreatedEvent event = new OrderCreatedEvent(order.getId(), order.getUserId(), order.getProduct(), order.getPrice());
        rabbitTemplate.convertAndSend("orders.v1.order-created", "", event); 
        return order;
    }
}