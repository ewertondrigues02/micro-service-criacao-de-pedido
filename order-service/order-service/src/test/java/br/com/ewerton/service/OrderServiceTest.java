package br.com.ewerton.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.ewerton.model.Order;
import br.com.ewerton.repository.OrderRepository;

public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    private Order order;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Cria um pedido para os testes
        order = new Order(1L, 1L, "Produto X", 100.0);
    }

    @Test
    void testAllOrders() {
        // Mocka o comportamento do repositório para retornar uma lista de pedidos
        when(orderRepository.findAll()).thenReturn(Arrays.asList(order));

        // Chama o método allOrder e verifica se a resposta está correta
        List<Order> orders = orderService.allOrder();
        assertNotNull(orders);
        assertEquals(1, orders.size());  // Espera que a lista tenha 1 pedido
        assertEquals(order, orders.get(0));  // Verifica se o pedido retornado é o mesmo

        // Verifica se o método do repositório foi chamado corretamente
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    void testCreateOrder() {
        // Mocka o comportamento do repositório para salvar o pedido
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        // Chama o método createOrder e verifica se o pedido foi salvo corretamente
        Order savedOrder = orderService.createOrder(order);
        assertNotNull(savedOrder);
        assertEquals(order.getId(), savedOrder.getId());  // Verifica se o ID do pedido é igual

        // Verifica se o método do repositório foi chamado corretamente
        verify(orderRepository, times(1)).save(any(Order.class));
    }
}
