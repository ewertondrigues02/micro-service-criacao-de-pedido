package br.com.ewerton.configuration;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import br.com.ewerton.dto.OrderCreatedEvent;

@Component
public class OrderCreatedListener {

	@RabbitListener(queues = "orders.v1.order-created.send.notification")
	public void onOrderCreated(OrderCreatedEvent event) {
		System.out.println("Pedido do seu produto recebido  " + event.product());
		System.out.println("Confira o valor Total do seu pedido " + event.price());
		System.out.println("Id recebido " + event.id());

	}
}
