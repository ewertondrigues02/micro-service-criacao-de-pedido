package br.com.ewerton.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.ewerton.dto.OrderCreatedEvent;
import br.com.ewerton.model.Order;
import br.com.ewerton.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OrderControllerTest {

    private MockMvc mockMvc;

    @Mock
    private OrderService orderService;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private OrderController orderController;

    private Order order;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();

        // Cria um pedido para os testes
        order = new Order(1L, 1L, "Produto X", 100.0);
    }

    @Test
    void testCreateOrder() throws Exception {
        // Mocka o comportamento do serviço para criar um pedido
        when(orderService.createOrder(any(Order.class))).thenReturn(order);

        // Cria o corpo da requisição com o objeto Order
        ObjectMapper objectMapper = new ObjectMapper();
        String orderJson = objectMapper.writeValueAsString(order);

        // Faz uma requisição POST e verifica se a resposta está correta
        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(orderJson))
                .andExpect(status().isOk())  // Espera status 200 OK
                .andExpect(jsonPath("$.id").value(order.getId()))  // Verifica o ID do pedido
                .andExpect(jsonPath("$.userId").value(order.getUserId()))  // Verifica o userId
                .andExpect(jsonPath("$.product").value(order.getProduct()))  // Verifica o produto
                .andExpect(jsonPath("$.price").value(order.getPrice()));  // Verifica o preço

        // Verifica se o método do serviço foi chamado corretamente
        verify(orderService, times(1)).createOrder(any(Order.class));

        // Verifica se o evento foi enviado ao RabbitMQ
        verify(rabbitTemplate, times(1)).convertAndSend(eq("orders.v1.order-created"), eq(""), any(OrderCreatedEvent.class));
    }
}
