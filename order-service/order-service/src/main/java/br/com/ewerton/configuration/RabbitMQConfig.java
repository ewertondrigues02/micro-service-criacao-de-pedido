package br.com.ewerton.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Classe de configuração do RabbitMQ.
 * Configura a fila, a exchange, os bindings e o template de mensagens.
 */
@Configuration
public class RabbitMQConfig {

    /**
     * Cria uma FanoutExchange para a comunicação de eventos de pedidos criados.
     *
     * @return Instância da exchange configurada.
     */
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("orders.v1.order-created");
    }

    /**
     * Cria uma fila para armazenar pedidos criados.
     *
     * @return Instância da fila configurada.
     */
    @Bean
    Queue queueOrder() {
        return new Queue("orders.v1.order-created.send.order");
    }

    /**
     * Configura o RabbitAdmin, que é responsável por gerenciar a configuração do RabbitMQ.
     *
     * @param connectionFactory Fábrica de conexões com o RabbitMQ.
     * @return Instância do RabbitAdmin.
     */
    @Bean
    RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    /**
     * Inicializa o RabbitAdmin ao iniciar a aplicação.
     *
     * @param rabbitAdmin Instância do RabbitAdmin.
     * @return ApplicationListener que executa a inicialização do RabbitAdmin.
     */
    @Bean
    ApplicationListener<ApplicationReadyEvent> applicationReadyEventApplicationListener(RabbitAdmin rabbitAdmin) {
        return event -> rabbitAdmin.initialize();
    }

    /**
     * Cria um binding entre a fila de pedidos e a FanoutExchange.
     *
     * @param queueOrder     Fila configurada.
     * @param fanoutExchange Exchange configurada.
     * @return Instância do Binding configurado.
     */
    @Bean
    Binding binding(Queue queueOrder, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queueOrder).to(fanoutExchange);
    }

    /**
     * Configura um conversor de mensagens para JSON usando Jackson.
     *
     * @return Instância do Jackson2JsonMessageConverter.
     */
    @Bean
    Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /**
     * Configura o RabbitTemplate com um conversor de mensagens JSON.
     *
     * @param connectionFactory Fábrica de conexões com o RabbitMQ.
     * @param messageConverter  Conversor de mensagens para JSON.
     * @return Instância do RabbitTemplate configurada.
     */
    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }
}
