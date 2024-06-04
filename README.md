# Micro Serviço de Pedido

### Descrição:
 * Micro Serviço: **User Service** fará um pedido via requisição HTTP com metodo **POST** para o micro serviço **Order Service** onde será armazenado na base de dados **Postgres**, onde o Order Service recebera o pedido via Mensageria onde o **RabbitMQ** recebera na Exchange padrão **Fanout** do próprio RabbitMQ pois como é um projeto simples não vi necessidade de criar um filtro, por sua vez os micro serviços que estiverem conectados  nessa exchange recebera a mensagem na qual será os micro serviços: **Order Service** e **Notification Service** e o **User Service** recera uma mensagem dizendo que o pedido foi feito.



![micro service pedido2](https://github.com/ewertondrigues02/micro-service-criacao-de-pedido/assets/106437473/c76e1a44-8c37-44e2-96d5-dc2730defdcd)

