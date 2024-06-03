package br.com.ewerton.configuration;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import br.com.ewerton.dto.OrderCreatedEvent;

@Component
public class OrderCreatedListener {

	@RabbitListener(queues = "orders.v1.order-created.send.order")
	public void onOrderCreated(OrderCreatedEvent event) {
		System.out.println("Id recebido " + event.product());
		System.out.println("Id recebido " + event.price());
		System.out.println("Id recebido " + event.id());

	}
}
