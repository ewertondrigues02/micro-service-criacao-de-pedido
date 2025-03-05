package br.com.ewerton.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ewerton.model.Order;
import br.com.ewerton.repository.OrderRepository;

/**
 * Serviço para gerenciar operações relacionadas a pedidos (Order).
 */
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    /**
     * Retorna todos os pedidos cadastrados.
     *
     * @return Lista de pedidos.
     */
    public List<Order> allOrder() {
        return orderRepository.findAll();
    }

    /**
     * Cria e salva um novo pedido no banco de dados.
     *
     * @param order Objeto do pedido a ser salvo.
     * @return Pedido salvo.
     */
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }
}
