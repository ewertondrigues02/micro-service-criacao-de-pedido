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
 * Configuração do RabbitMQ para troca de mensagens entre serviços.
 */
@Configuration
public class RabbitMQConfig {

    /**
     * Define a Fanout Exchange para a comunicação de pedidos criados.
     *
     * @return Instância da FanoutExchange.
     */
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("orders.v1.order-created");
    }

    /**
     * Define a fila para envio de pedidos.
     *
     * @return Instância da fila de pedidos.
     */
    @Bean
    Queue queueOrder() {
        return new Queue("orders.v1.order-created.send.order");
    }

    /**
     * Define a fila para envio de notificações.
     *
     * @return Instância da fila de notificações.
     */
    @Bean
    Queue queueNotification() {
        return new Queue("orders.v1.order-created.send.notification");
    }

    /**
     * Configura o RabbitAdmin para gerenciar a criação automática de filas e exchanges.
     *
     * @param connectionFactory Fábrica de conexões do RabbitMQ.
     * @return Instância do RabbitAdmin.
     */
    @Bean
    RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    /**
     * Listener para inicializar as configurações do RabbitMQ quando a aplicação estiver pronta.
     *
     * @param rabbitAdmin Instância do RabbitAdmin.
     * @return Listener de eventos de aplicação.
     */
    @Bean
    ApplicationListener<ApplicationReadyEvent> applicationReadyEventApplicationListener(RabbitAdmin rabbitAdmin) {
        return event -> rabbitAdmin.initialize();
    }

    /**
     * Cria a vinculação da fila de pedidos à Fanout Exchange.
     *
     * @return Instância do Binding.
     */
    @Bean
    Binding bindingOrder() {
        return BindingBuilder.bind(queueOrder()).to(fanoutExchange());
    }

    /**
     * Cria a vinculação da fila de notificações à Fanout Exchange.
     *
     * @return Instância do Binding.
     */
    @Bean
    Binding bindingNotification() {
        return BindingBuilder.bind(queueNotification()).to(fanoutExchange());
    }

    /**
     * Define o conversor de mensagens para JSON.
     *
     * @return Instância do Jackson2JsonMessageConverter.
     */
    @Bean
    Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /**
     * Configura o RabbitTemplate para uso com o conversor de mensagens JSON.
     *
     * @param connectionFactory Fábrica de conexões do RabbitMQ.
     * @param messageConverter  Conversor de mensagens JSON.
     * @return Instância do RabbitTemplate.
     */
    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }
}
