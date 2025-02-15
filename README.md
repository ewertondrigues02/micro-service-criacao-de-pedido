# Micro Serviço de Pedido


### Descrição:
 * Micro Serviço: **User Service** fará um pedido via requisição HTTP com metodo **POST** para o micro serviço **Order Service** onde será armazenado na base de dados **Postgres**, onde o Order Service recebera o pedido via Mensageria onde o **RabbitMQ** recebera na Exchange padrão **Fanout** do próprio RabbitMQ pois como é um projeto simples não vi necessidade de criar um filtro, por sua vez os micro serviços que estiverem conectados  nessa exchange recebera a mensagem na qual será os micro serviços: **Order Service** e **Notification Service** e o **User Service** recera uma mensagem dizendo que o pedido foi feito.

### Tecnologias e Ferramentas Usadas:
 * **Spring**: DevTools, Amqp, Actuator, Jpa, Web, Postgres.
 * **RabbitMQ**: para mensagerias  entre micro serviço, versão 3.7.
 * **Docker**: para criar containes do RabbitMQ e Postgres facilitando o gerenciamento do ambiente.
 * **Eclipse**: para desenvolvimento dos código.
 * **Postman**: para testar requisições HTTP.
 * **GitHub**: Utilizado para armazenar os repositórios do projeto, facilitando o versionamento e colaboração.

### Padrões e Conceitos Usados:
 * Padrão **MVC**: para separar as camadas das aplicações.
 * Padrão **REST**: para sistemas distribuídos, que utiliza o protocolo HTTP para comunicação, com operações baseadas em recursos identificados por URLs e métodos HTTP (GET, POST, PUT, DELETE), promovendo uma interface uniforme e orientada a recursos.
 * Conceito **Clean Code**: para escrever código de forma clara, legível e organizada, facilitando sua compreensão, manutenção e colaboração entre desenvolvedores.
 * Principio **SOLID** foi usado: **S** - Single Responsibility Principle (Princípio da Responsabilidade Única): Uma classe deve ter apenas uma única razão para mudar.
**O** - Open/Closed Principle (Princípio do Aberto/Fechado): Entidades de software devem ser abertas para extensão, mas fechadas para modificação.
**L** - Liskov Substitution Principle (Princípio da Substituição de Liskov): Subtipos devem ser substituíveis por seus tipos base.



![micro service pedido2](https://github.com/ewertondrigues02/micro-service-criacao-de-pedido/assets/106437473/c76e1a44-8c37-44e2-96d5-dc2730defdcd)

